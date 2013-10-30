package net.raven.scrum.ui.service.scrum;

import java.util.List;

import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;
import net.raven.scrum.core.rest.dto.scrum.SubtaskDTO;
import net.raven.scrum.core.rest.dto.user.ScrumUserDTO;

public interface ScrumService
{
	public ProjectDTO prepareDataForScrumboard(Long idProject)
			throws ScrumException;

	public SubtaskDTO changeTaskState(SubtaskDTO subtaskDTO)
			throws ScrumException;

	public SubtaskDTO addSubtaskQuick(Long idParent, SubtaskDTO subtaskDTO)
			throws ScrumException;

	public List<ProjectDTO> getProjectList() throws ScrumException;

	public List<ScrumUserDTO> getProjectUsers(Long idProject)
			throws ScrumException;

	public List<ProjectDTO> getProjectListForUser(String login)
			throws ScrumException;

}
