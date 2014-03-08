package net.raven.scrum.core.rest.dto.scrum;

import java.util.Collection;

import net.raven.scrum.core.enumeration.scrum.ProjectStatus;
import net.raven.scrum.core.rest.dto.user.ScrumUserDTO;

public class ProjectDTO
{
	private long idProject;

	private String title;

	private String description;

	private SprintDTO sprint;

	private ProjectStatus status;

	private Collection<ScrumUserDTO> projectUsers;

	private Collection<EpicDTO> epics;

	public ProjectDTO()
	{

	}

	public long getIdProject()
	{
		return idProject;
	}

	public void setIdProject(long idProject)
	{
		this.idProject = idProject;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public SprintDTO getSprint()
	{
		return sprint;
	}

	public void setSprint(SprintDTO sprint)
	{
		this.sprint = sprint;
	}

	public Collection<ScrumUserDTO> getProjectUsers()
	{
		return projectUsers;
	}

	public void setProjectUsers(Collection<ScrumUserDTO> projectUsers)
	{
		this.projectUsers = projectUsers;
	}

	public ProjectStatus getStatus()
	{
		return status;
	}

	public void setStatus(ProjectStatus status)
	{
		this.status = status;
	}

	public Collection<EpicDTO> getEpics()
	{
		return epics;
	}

	public void setEpics(Collection<EpicDTO> epics)
	{
		this.epics = epics;
	}

}
