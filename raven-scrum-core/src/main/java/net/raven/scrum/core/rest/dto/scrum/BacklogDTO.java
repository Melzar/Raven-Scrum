package net.raven.scrum.core.rest.dto.scrum;

import java.util.List;

public class BacklogDTO
{
	private SprintDTO sprintdata;

	private ProjectDTO projectdata;

	private long idBacklog;

	private List<TaskDTO> backlogtasks;

	public BacklogDTO()
	{

	}

	public SprintDTO getSprintdata()
	{
		return sprintdata;
	}

	public void setSprintdata(SprintDTO sprintdata)
	{
		this.sprintdata = sprintdata;
	}

	public ProjectDTO getProjectdata()
	{
		return projectdata;
	}

	public void setProjectdata(ProjectDTO projectdata)
	{
		this.projectdata = projectdata;
	}

	public long getIdBacklog()
	{
		return idBacklog;
	}

	public void setIdBacklog(long idBacklog)
	{
		this.idBacklog = idBacklog;
	}

	public List<TaskDTO> getBacklogtasks()
	{
		return backlogtasks;
	}

	public void setBacklogtasks(List<TaskDTO> backlogtasks)
	{
		this.backlogtasks = backlogtasks;
	}

}
