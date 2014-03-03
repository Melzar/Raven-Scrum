package net.raven.scrum.core.rest.dto.scrum;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import net.raven.scrum.core.enumeration.scrum.SprintStatus;

public class SprintDTO
{

	private long id;

	private long idProject;

	private Date startDate;

	private Date endDate;

	Collection<TaskDTO> tasks;

	private SprintStatus status;

	public SprintDTO()
	{
		tasks = new LinkedList<>();
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Collection<TaskDTO> getTasks()
	{
		return tasks;
	}

	public void setTasks(Collection<TaskDTO> tasks)
	{
		this.tasks = tasks;
	}

	public SprintStatus getStatus()
	{
		return status;
	}

	public void setStatus(SprintStatus status)
	{
		this.status = status;
	}

	public long getIdProject()
	{
		return idProject;
	}

	public void setIdProject(long idProject)
	{
		this.idProject = idProject;
	}

}
