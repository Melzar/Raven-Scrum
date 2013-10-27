package net.raven.scrum.core.rest.dto.scrum;

import java.util.Collection;
import java.util.Date;

public class SprintDTO
{

	private long id;

	private Date startDate;

	private Date endDate;

	Collection<TaskDTO> tasks;

	public SprintDTO()
	{

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

}
