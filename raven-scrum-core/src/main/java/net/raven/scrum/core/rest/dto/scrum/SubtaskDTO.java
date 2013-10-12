package net.raven.scrum.core.rest.dto.scrum;

import net.raven.scrum.core.enumeration.scrum.TaskState;
import net.raven.scrum.core.enumeration.scrum.TaskType;

public class SubtaskDTO
{

	private long idTask;

	private long idUser;

	private String title;

	private String description;

	private TaskState state;

	private TaskType type;

	public SubtaskDTO()
	{

	}

	public long getIdTask()
	{
		return idTask;
	}

	public void setIdTask(long idTask)
	{
		this.idTask = idTask;
	}

	public long getIdUser()
	{
		return idUser;
	}

	public void setIdUser(long idUser)
	{
		this.idUser = idUser;
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

	public TaskState getState()
	{
		return state;
	}

	public void setState(TaskState state)
	{
		this.state = state;
	}

	public TaskType getType()
	{
		return type;
	}

	public void setType(TaskType type)
	{
		this.type = type;
	}

}
