package net.raven.scrum.ui.service.scrum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.raven.scrum.core.entity.ScrumBacklog;
import net.raven.scrum.core.entity.ScrumColor;
import net.raven.scrum.core.entity.ScrumEpic;
import net.raven.scrum.core.entity.ScrumProject;
import net.raven.scrum.core.entity.ScrumSprint;
import net.raven.scrum.core.entity.ScrumTask;
import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.entity.ScrumUserProjectRole;
import net.raven.scrum.core.enumeration.scrum.ProjectRole;
import net.raven.scrum.core.enumeration.scrum.SprintStatus;
import net.raven.scrum.core.enumeration.scrum.TaskState;
import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.repository.ScrumBacklogRepository;
import net.raven.scrum.core.repository.ScrumColorRepository;
import net.raven.scrum.core.repository.ScrumEpicRepository;
import net.raven.scrum.core.repository.ScrumProjectRepository;
import net.raven.scrum.core.repository.ScrumSprintRepository;
import net.raven.scrum.core.repository.ScrumTaskRepository;
import net.raven.scrum.core.repository.ScrumUserRepository;
import net.raven.scrum.core.rest.dto.scrum.BacklogDTO;
import net.raven.scrum.core.rest.dto.scrum.ColorDTO;
import net.raven.scrum.core.rest.dto.scrum.EpicDTO;
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

	@Autowired
	private ScrumEpicRepository epicRepository;

	@Autowired
	private ScrumColorRepository colorRepository;

	@Autowired
	private ScrumBacklogRepository backlogRepository;

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
				taskdto.setShowChildren(true);
				taskdto.setIdSprint(ss.getIdSprint());
				taskdto.setIdProject(project.getIdProject());
				for (ScrumTask subtask : task.getSubtasks())
				{
					TaskDTO subdto = new TaskDTO();
					subdto.setId(subtask.getIdTask());
					subdto.setIdParent(task.getIdTask());
					subdto.setIdUser((subtask.getAssigned() != null) ? subtask
							.getAssigned().getIdUser() : 0);
					subdto.setTitle(subtask.getTitle());
					subdto.setDescription(subtask.getDescription());
					subdto.setState(subtask.getState());
					subdto.setType(subtask.getType());
					subdto.setIdSprint(ss.getIdSprint());
					subdto.setIdProject(project.getIdProject());
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
		taskRepository.attachSubtaskToParent(idParent, subtask.getIdTask());
		subtaskDTO.setId(subtask.getIdTask());
		subtaskDTO.setTitle(subtask.getTitle());
		subtaskDTO.setState(subtask.getState());
		subtaskDTO.setIdSprint(sprint.getIdSprint());
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

	@Override
	public BacklogDTO prepareBacklogData(Long idProject) throws ScrumException
	{
		BacklogDTO backlogdto = new BacklogDTO();
		List<TaskDTO> tasklist = new LinkedList<>();
		ScrumSprint ss = sprintRepository.getSprintData(idProject,
				SprintStatus.ACTIVE);
		ScrumBacklog bl = backlogRepository.getBacklogForProject(idProject);
		SprintDTO sprintDTO = new SprintDTO();
		sprintDTO.setId(ss.getIdSprint());
		sprintDTO.setStartDate(ss.getStartDate());
		sprintDTO.setEndDate(ss.getEndDate());
		sprintDTO.setStatus(ss.getStatus());
		for (ScrumTask task : ss.getTasks())
		{
			TaskDTO taskdto = new TaskDTO();
			taskdto.setId(task.getIdTask());
			taskdto.setIdUser((task.getAssigned() != null) ? task.getAssigned()
					.getIdUser() : 0);
			taskdto.setTitle(task.getTitle());
			taskdto.setDescription(task.getDescription());
			taskdto.setState(TaskState.DONE);
			for (ScrumTask subtask : task.getSubtasks())
			{
				TaskDTO subdto = new TaskDTO();
				subdto.setId(subtask.getIdTask());
				subdto.setIdParent(task.getIdTask());
				subdto.setIdUser((subtask.getAssigned() != null) ? subtask
						.getAssigned().getIdUser() : 0);
				subdto.setTitle(subtask.getTitle());
				subdto.setDescription(subtask.getDescription());
				subdto.setState(subtask.getState());
				subdto.setType(subtask.getType());
				if (subdto.getState() != TaskState.DONE)
					taskdto.setState(subdto.getState());
				taskdto.getSubtasksRaw().add(subdto);
			}
			sprintDTO.getTasks().add(taskdto);
		}
		for (ScrumTask task : bl.getTasks())
		{
			TaskDTO taskdto = new TaskDTO();
			taskdto.setId(task.getIdTask());
			taskdto.setTitle(task.getTitle());
			taskdto.setDescription(task.getDescription());
			backlogdto.getBacklogtasks().add(taskdto);
		}
		backlogdto.setSprintdata(sprintDTO);
		return backlogdto;
	}

	@Override
	public Collection<EpicDTO> getProjectEpicsList(long idProject)
			throws ScrumException
	{
		Collection<ScrumEpic> epicsRaw = epicRepository
				.getProjectEpics(idProject);
		Collection<EpicDTO> epics = new LinkedList<>();
		for (ScrumEpic epic : epicsRaw)
		{
			EpicDTO dto = new EpicDTO();
			ColorDTO cdto = new ColorDTO();
			ScrumColor colorRaw = epic.getColor();
			cdto.setCode(colorRaw.getCode());
			cdto.setIdColor(colorRaw.getIdColor());
			dto.setColor(cdto);
			dto.setIdEpic(epic.getIdEpic());
			dto.setEpicName(epic.getEpicName());
			epics.add(dto);
		}
		return epics;
	}

	@Override
	public EpicDTO addEpicToProject(EpicDTO epic) throws ScrumException
	{
		ScrumEpic ep = new ScrumEpic();
		ScrumProject proj = projectRepository.findOne(epic.getProject()
				.getIdProject());
		ScrumColor color = colorRepository
				.findOne(epic.getColor().getIdColor());
		ep.setColor(color);
		ep.setEpicName(epic.getEpicName());
		ep.setProject(proj);
		ep = epicRepository.save(ep);
		epic.setIdEpic(ep.getIdEpic());
		return epic;
	}

	@Override
	public Collection<ColorDTO> getAllAvailableColors() throws ScrumException
	{
		List<ScrumColor> colorsRaw = colorRepository.findAll();
		Collection<ColorDTO> colors = new LinkedList<>();
		for (ScrumColor color : colorsRaw)
		{
			ColorDTO c = new ColorDTO();
			c.setIdColor(color.getIdColor());
			c.setCode(color.getCode());
			colors.add(c);
		}
		return colors;
	}

	@Override
	public EpicDTO updateEpicColor(EpicDTO epic) throws ScrumException
	{
		epicRepository.updateEpicColor(epic.getIdEpic(), epic.getColor()
				.getIdColor());
		return epic;
	}

	@Override
	public EpicDTO updateEpicName(EpicDTO epic) throws ScrumException
	{
		epicRepository.updateEpicName(epic.getEpicName(), epic.getIdEpic());
		return epic;
	}

	@Override
	public void deleteEpic(long idEpic) throws ScrumException
	{
		epicRepository.delete(idEpic);
	}

	@Override
	public TaskDTO editTaskDescription(TaskDTO taskDTO) throws ScrumException
	{
		taskRepository.updateTaskDescription(taskDTO.getId(),
				taskDTO.getDescription());
		return taskDTO;
	}

	@Override
	public TaskDTO changeTaskType(TaskDTO subtaskDTO) throws ScrumException
	{
		taskRepository.updateTaskType(subtaskDTO.getType(), subtaskDTO.getId());
		return subtaskDTO;
	}

	@Override
	public TaskDTO changeTaskUser(TaskDTO subtaskDTO) throws ScrumException
	{
		taskRepository.updateTaskUser(subtaskDTO.getIdUser(),
				subtaskDTO.getId());
		return subtaskDTO;
	}

	@Override
	public TaskDTO scopeOutTask(TaskDTO subtaskDTO) throws ScrumException
	{
		ScrumBacklog backlog = backlogRepository
				.getBacklogForProject(subtaskDTO.getIdProject());
		ScrumTask task = taskRepository.getTaskWithSprint(subtaskDTO.getId());
		task.getSprints().clear();
		taskRepository.detachSubtaskFromParent(subtaskDTO.getId());
		backlog.getTasks().add(task);
		task.getBacklog().add(backlog);
		taskRepository.saveAndFlush(task);
		subtaskDTO.getSubtasksRaw().clear();
		return subtaskDTO;
	}

	@Override
	public TaskDTO changeTaskParent(TaskDTO subtaskDTO) throws ScrumException
	{

		ScrumTask task = taskRepository.getTaskWithBacklog(subtaskDTO.getId());
		if (task.getBacklog() != null)
		{
			task.setBacklog(null);
			ScrumSprint sprint = taskRepository.getTaskActiveSprint(subtaskDTO
					.getIdParent());
			Set<ScrumSprint> sprints = new HashSet<ScrumSprint>();
			sprints.add(sprint);
			task.setSprints(sprints);
		}
		task.setState(subtaskDTO.getState());
		taskRepository.saveAndFlush(task);
		taskRepository.attachSubtaskToParent(subtaskDTO.getIdParent(),
				subtaskDTO.getId());
		return subtaskDTO;
	}
}
