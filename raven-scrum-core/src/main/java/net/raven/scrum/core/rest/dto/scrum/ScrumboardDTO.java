package net.raven.scrum.core.rest.dto.scrum;

public class ScrumboardDTO
{
	private long idProject;

	private long idManager;

	private String title;

	private String description;

	private SprintDTO sprint;

	public ScrumboardDTO()
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

}
