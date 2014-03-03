package net.raven.scrum.ui.service.scrum;

import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.BacklogDTO;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;
import net.raven.scrum.core.rest.dto.scrum.SprintDTO;
import net.raven.scrum.core.rest.dto.scrum.TaskDTO;

public interface ScrumService
{
	public ProjectDTO prepareDataForScrumboard(Long idProject)
			throws ScrumException;

	public BacklogDTO prepareBacklogData(Long idProject) throws ScrumException;

	public TaskDTO changeTaskState(TaskDTO subtaskDTO) throws ScrumException;

	public TaskDTO deleteTask(TaskDTO subtaskDTO) throws ScrumException;

	public TaskDTO makeSubtaskParentTask(TaskDTO subtaskDTO)
			throws ScrumException;

	public TaskDTO addSubtaskQuick(Long idParent, TaskDTO subtaskDTO)
			throws ScrumException;

	public SprintDTO createNewSprint(SprintDTO sprintDTO) throws ScrumException;

	public SprintDTO closeActiveSprint(SprintDTO sprintDTO)
			throws ScrumException;

}
