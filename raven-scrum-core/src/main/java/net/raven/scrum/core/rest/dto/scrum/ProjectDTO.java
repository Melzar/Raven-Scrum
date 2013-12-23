package net.raven.scrum.core.rest.dto.scrum;

import java.util.Collection;

import net.raven.scrum.core.rest.dto.user.ScrumUserDTO;

public class ProjectDTO
{
	private long idProject;

	private long idManager;

	private String title;

	private String description;

	private SprintDTO sprint;

	private Collection<ScrumUserDTO> projectUsers;

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

	public long getIdManager()
	{
		return idManager;
	}

	public void setIdManager(long idManager)
	{
		this.idManager = idManager;
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

}
