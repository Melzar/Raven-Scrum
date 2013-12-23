package net.raven.scrum.ui.service.project;

import java.util.Collection;
import java.util.List;

import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;
import net.raven.scrum.core.rest.dto.user.ScrumUserDTO;

public interface ProjectService
{

	public List<ProjectDTO> getProjectList() throws ScrumException;

	public Collection<ScrumUserDTO> getProjectUsers(Long idProject)
			throws ScrumException;

	public List<ProjectDTO> getProjectListForUser(String login)
			throws ScrumException;

}
