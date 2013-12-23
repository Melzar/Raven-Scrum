package net.raven.scrum.ui.service.scrum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.raven.scrum.core.entity.ScrumSprint;
import net.raven.scrum.core.entity.ScrumTask;
import net.raven.scrum.core.entity.ScrumUser;
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
		ProjectDTO boarddto = new ProjectDTO();
		SprintDTO sprintdto = new SprintDTO();
		boarddto.setIdProject(ss.getProject().getIdProject());
		boarddto.setIdManager(ss.getProject().getManager().getIdUser());
		sprintdto.setId(ss.getIdSprint());
		sprintdto.setStartDate(ss.getStartDate());
		sprintdto.setEndDate(ss.getEndDate());
		Collection<TaskDTO> tasks = new ArrayList<TaskDTO>();
		for (ScrumTask task : ss.getTasks())
		{
			TaskDTO taskdto = new TaskDTO();
			taskdto.setId(task.getIdTask());
			taskdto.setIdUser(task.getAssigned().getIdUser());
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
		boarddto.setSprint(sprintdto);
		return boarddto;
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
}
