package net.raven.scrum.ui.service.scrum;

import java.util.List;

import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;
import net.raven.scrum.core.rest.dto.scrum.TaskDTO;
import net.raven.scrum.core.rest.dto.user.ScrumUserDTO;

public interface ScrumService
{
	public ProjectDTO prepareDataForScrumboard(Long idProject)
			throws ScrumException;

	public TaskDTO changeTaskState(TaskDTO subtaskDTO)
			throws ScrumException;

	public TaskDTO deleteTask(TaskDTO subtaskDTO) throws ScrumException;

	public TaskDTO addSubtaskQuick(Long idParent, TaskDTO subtaskDTO)
			throws ScrumException;

	public List<ProjectDTO> getProjectList() throws ScrumException;

	public List<ScrumUserDTO> getProjectUsers(Long idProject)
			throws ScrumException;

	public List<ProjectDTO> getProjectListForUser(String login)
			throws ScrumException;

}
