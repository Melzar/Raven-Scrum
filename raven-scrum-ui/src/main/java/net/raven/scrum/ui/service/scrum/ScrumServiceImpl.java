package net.raven.scrum.ui.service.scrum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import net.raven.scrum.core.entity.ScrumProject;
import net.raven.scrum.core.entity.ScrumSprint;
import net.raven.scrum.core.entity.ScrumTask;
import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.entity.ScrumUserProjectRole;
import net.raven.scrum.core.enumeration.scrum.ProjectRole;
import net.raven.scrum.core.enumeration.scrum.SprintStatus;
import net.raven.scrum.core.enumeration.scrum.TaskState;
import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.repository.ScrumProjectRepository;
import net.raven.scrum.core.repository.ScrumSprintRepository;
import net.raven.scrum.core.repository.ScrumTaskRepository;
import net.raven.scrum.core.repository.ScrumUserRepository;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;
import net.raven.scrum.core.rest.dto.scrum.SprintDTO;
import net.raven.scrum.core.rest.dto.scrum.TaskDTO;
import net.raven.scrum.core.rest.dto.user.ScrumUserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrumServiceImpl implements ScrumService
{
	@Autowired
	private ScrumSprintRepository sprintRepository;

	@Autowired
	private ScrumTaskRepository taskRepository;

	@Autowired
	private ScrumUserRepository userRepository;

	@Autowired
	private ScrumProjectRepository projectRepository;

	public ScrumServiceImpl()
	{

	}

	public ProjectDTO prepareDataForScrumboard(Long idProject)
			throws ScrumException
	{
		ScrumSprint ss = sprintRepository.getSprintData(idProject,
				SprintStatus.ACTIVE);
		ProjectDTO projectdto = new ProjectDTO();
		ScrumProject project = projectRepository.getProjectWithUsers(idProject);
		if (project != null)
		{
			projectdto.setTitle(project.getTitle());
			projectdto.setDescription((project.getDescription()));
			projectdto.setIdProject(project.getIdProject());
			projectdto.setStatus(project.getStatus());
			Collection<ScrumUserDTO> users = new LinkedList<>();
			for (ScrumUserProjectRole user : project.getUserProjectRole())
			{
				ScrumUserDTO udto = new ScrumUserDTO();
				udto.setId(user.getPk().getUser().getIdUser());
				udto.setLogin(user.getPk().getUser().getLogin());
				udto.setName(user.getPk().getUser().getName());
				udto.setSurname(user.getPk().getUser().getSurname());
				udto.setRole(ProjectRole.valueOf(user.getPk().getRole()
						.getRoleName()));
				users.add(udto);
			}
			projectdto.setProjectUsers(users);
		}
		if (ss != null)
		{
			SprintDTO sprintdto = new SprintDTO();
			Collection<TaskDTO> tasks = new ArrayList<TaskDTO>();
			projectdto.setIdProject(ss.getProject().getIdProject());
			sprintdto.setId(ss.getIdSprint());
			sprintdto.setStartDate(ss.getStartDate());
			sprintdto.setEndDate(ss.getEndDate());
			for (ScrumTask task : ss.getTasks())
			{
				TaskDTO taskdto = new TaskDTO();
				taskdto.setId(task.getIdTask());
				taskdto.setIdUser((task.getAssigned() != null) ? task
						.getAssigned().getIdUser() : 0);
				taskdto.setTitle(task.getTitle());
				taskdto.setDescription(task.getDescription());
				for (ScrumTask subtask : task.getSubtasks())
				{
					TaskDTO subdto = new TaskDTO();
					subdto.setId(subtask.getIdTask());
					subdto.setIdParent(task.getIdTask());
					subdto.setIdUser(subtask.getAssigned().getIdUser());
					subdto.setTitle(subtask.getTitle());
					subdto.setDescription(subtask.getDescription());
					subdto.setState(subtask.getState());
					subdto.setType(subtask.getType());
					taskdto.getProgress().get(subtask.getState()).add(subdto);
				}
				tasks.add(taskdto);
			}
			sprintdto.setTasks(tasks);
			projectdto.setSprint(sprintdto);
		}
		return projectdto;
	}

	@Override
	public TaskDTO addSubtaskQuick(Long idParent, TaskDTO subtaskDTO)
			throws ScrumException
	{
		ScrumTask subtask = new ScrumTask();
		Set<ScrumSprint> scrumsprint = new HashSet<ScrumSprint>();
		ScrumUser user = userRepository.findOne(subtaskDTO.getIdUser());
		ScrumSprint sprint = taskRepository.getTaskActiveSprint(idParent);
		scrumsprint.add(sprint);
		subtask.setTitle(sprint.getProject().getTitle());
		subtask.setAssigned(user);
		subtask.setDescription(subtaskDTO.getDescription());
		subtask.setType(subtaskDTO.getType());
		subtask.setSprints(scrumsprint);
		subtask.setState(TaskState.TODO);
		subtask = taskRepository.save(subtask);
		taskRepository.setParentTaskForSubtask(idParent, subtask.getIdTask());
		subtaskDTO.setId(subtask.getIdTask());
		subtaskDTO.setTitle(subtask.getTitle());
		subtaskDTO.setState(subtask.getState());
		return subtaskDTO;
	}

	@Override
	public TaskDTO changeTaskState(TaskDTO subtaskDTO) throws ScrumException
	{
		taskRepository.setTaskState(subtaskDTO.getState(), subtaskDTO.getId());
		return subtaskDTO;
	}

	@Override
	public TaskDTO deleteTask(TaskDTO subtaskDTO) throws ScrumException
	{
		taskRepository.delete(subtaskDTO.getId());
		return subtaskDTO;
	}

	@Override
	public TaskDTO makeSubtaskParentTask(TaskDTO subtaskDTO)
			throws ScrumException
	{
		subtaskDTO.setTitle(subtaskDTO.getTitle() + " - " + subtaskDTO.getId());
		taskRepository.makeSubtaskParentTask(subtaskDTO.getId(),
				subtaskDTO.getTitle(), subtaskDTO.getDescription(),
				subtaskDTO.getIdUser());
		return subtaskDTO;
	}

	@Override
	public SprintDTO createNewSprint(SprintDTO sprintDTO) throws ScrumException
	{
		ScrumSprint sprint = new ScrumSprint();
		ScrumProject project = new ScrumProject();
		sprint.setStartDate(sprintDTO.getStartDate());
		sprint.setEndDate(sprintDTO.getStartDate());
		project.setIdProject(sprintDTO.getIdProject());
		sprint.setProject(project);
		sprint.setStatus(SprintStatus.ACTIVE);
		sprint = sprintRepository.save(sprint);
		ScrumTask other = new ScrumTask();
		other.setTitle("OTHER");
		other.setDescription("Other tasks");
		other.setState(TaskState.TODO);
		Set<ScrumSprint> spr = new HashSet<ScrumSprint>();
		spr.add(sprint);
		other.setSprints(spr);
		taskRepository.save(other);
		sprintDTO.setId(sprint.getIdSprint());
		return sprintDTO;
	}

	@Override
	public SprintDTO closeActiveSprint(SprintDTO sprintDTO)
			throws ScrumException
	{
		ScrumSprint sprint = new ScrumSprint();
		sprint.setIdSprint(sprintDTO.getId());
		sprint.setStartDate(sprintDTO.getStartDate());
		sprint.setEndDate(sprintDTO.getStartDate());
		sprint.setStatus(SprintStatus.DONE);
		sprint = sprintRepository.save(sprint);
		sprintDTO.setStatus(SprintStatus.DONE);
		return sprintDTO;
	}
}
