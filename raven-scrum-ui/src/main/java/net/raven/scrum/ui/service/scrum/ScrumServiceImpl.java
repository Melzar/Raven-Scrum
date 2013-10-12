package net.raven.scrum.ui.service.scrum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.raven.scrum.core.entity.ScrumSprint;
import net.raven.scrum.core.entity.ScrumTask;
import net.raven.scrum.core.enumeration.scrum.SprintStatus;
import net.raven.scrum.core.repository.ScrumSprintRepository;
import net.raven.scrum.core.rest.dto.scrum.ScrumboardDTO;
import net.raven.scrum.core.rest.dto.scrum.SprintDTO;
import net.raven.scrum.core.rest.dto.scrum.SubtaskDTO;
import net.raven.scrum.core.rest.dto.scrum.TaskDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrumServiceImpl implements ScrumService
{
	@Autowired
	private ScrumSprintRepository sprintRepository;

	public ScrumServiceImpl()
	{

	}

	public ScrumboardDTO prepareDataForScrumboard(Long idProject)
	{
		ScrumSprint ss = sprintRepository.getSprintData(idProject,
				SprintStatus.ACTIVE);
		ScrumboardDTO boarddto = new ScrumboardDTO();
		SprintDTO sprintdto = new SprintDTO();
		boarddto.setIdProject(ss.getProject().getIdProject());
		boarddto.setIdManager(ss.getProject().getManager().getIdUser());
		sprintdto.setIdSprint(ss.getIdSprint());
		sprintdto.setStartDate(ss.getStartDate());
		sprintdto.setEndDate(ss.getEndDate());
		Collection<TaskDTO> tasks = new ArrayList<TaskDTO>();
		Map<Long, ArrayList<SubtaskDTO>> subtasks = new HashMap<Long, ArrayList<SubtaskDTO>>();
		for (ScrumTask task : ss.getTasks())
		{
			if (task.getParent() == null)
			{
				TaskDTO taskdto = new TaskDTO();
				taskdto.setIdTask(task.getIdTask());
				taskdto.setIdUser(task.getAssigned().getIdUser());
				taskdto.setTitle(task.getTitle());
				taskdto.setDescription(task.getDescription());
				tasks.add(taskdto);
			} else
			{
				SubtaskDTO subdto = new SubtaskDTO();
				subdto.setIdTask(task.getIdTask());
				subdto.setIdUser(task.getParent().getIdTask());
				subdto.setTitle(task.getTitle());
				subdto.setDescription(task.getDescription());
				subdto.setState(task.getState());
				subdto.setType(task.getType());
				if (subtasks.containsKey(task.getParent().getIdTask()))
				{
					subtasks.get(task.getParent().getIdTask()).add(subdto);
				} else
				{
					ArrayList<SubtaskDTO> list = new ArrayList<>();
					list.add(subdto);
					subtasks.put(task.getParent().getIdTask(), list);
				}
			}
		}
		for (TaskDTO taskdto : tasks)
		{
			for (SubtaskDTO sub : subtasks.get(taskdto.getIdTask()))
			{
				switch (sub.getState())
				{
				case DONE:
					taskdto.getDone().add(sub);
					;
					break;
				case DOING:
					taskdto.getDoing().add(sub);
					;
					break;
				case TODO:
					taskdto.getTodo().add(sub);
					;
					break;
				case UAT:
					taskdto.getUat().add(sub);
					;
					break;
				}
			}
		}
		sprintdto.setTasks(tasks);
		boarddto.setSprint(sprintdto);
		return boarddto;
	}
}
