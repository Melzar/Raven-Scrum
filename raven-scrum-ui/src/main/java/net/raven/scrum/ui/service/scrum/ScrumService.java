package net.raven.scrum.ui.service.scrum;

import java.util.Collection;

import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.BacklogDTO;
import net.raven.scrum.core.rest.dto.scrum.ColorDTO;
import net.raven.scrum.core.rest.dto.scrum.EpicDTO;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;
import net.raven.scrum.core.rest.dto.scrum.SprintDTO;
import net.raven.scrum.core.rest.dto.scrum.TaskDTO;

public interface ScrumService
{
	public ProjectDTO prepareDataForScrumboard(Long idProject)
			throws ScrumException;

	public BacklogDTO prepareBacklogData(Long idProject) throws ScrumException;

	public Collection<ColorDTO> getAllAvailableColors() throws ScrumException;

	public Collection<EpicDTO> getProjectEpicsList(long idProject)
			throws ScrumException;

	public TaskDTO editTaskDescription(TaskDTO taskDTO) throws ScrumException;

	public EpicDTO updateEpicColor(EpicDTO epic) throws ScrumException;

	public EpicDTO updateEpicName(EpicDTO epic) throws ScrumException;

	public EpicDTO addEpicToProject(EpicDTO epic) throws ScrumException;

	public void deleteEpic(long idEpic) throws ScrumException;

	public TaskDTO changeTaskState(TaskDTO subtaskDTO) throws ScrumException;

	public TaskDTO changeTaskType(TaskDTO subtaskDTO) throws ScrumException;

	public TaskDTO changeTaskUser(TaskDTO subtaskDTO) throws ScrumException;

	public TaskDTO deleteTask(TaskDTO subtaskDTO) throws ScrumException;

	public TaskDTO makeSubtaskParentTask(TaskDTO subtaskDTO)
			throws ScrumException;

	public TaskDTO scopeOutTask(TaskDTO subtaskDTO) throws ScrumException;

	public TaskDTO addSubtaskQuick(Long idParent, TaskDTO subtaskDTO)
			throws ScrumException;

	public SprintDTO createNewSprint(SprintDTO sprintDTO) throws ScrumException;

	public SprintDTO closeActiveSprint(SprintDTO sprintDTO)
			throws ScrumException;

}
