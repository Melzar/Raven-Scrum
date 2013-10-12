package net.raven.scrum.core.rest.dto.scrum;

import java.util.ArrayList;
import java.util.Collection;

public class TaskDTO
{
	private long idTask;

	private long idUser;

	private String title;

	private String description;

	private Collection<SubtaskDTO> todo;

	private Collection<SubtaskDTO> doing;

	private Collection<SubtaskDTO> uat;

	private Collection<SubtaskDTO> done;

	public TaskDTO()
	{
		todo = new ArrayList<>();
		doing = new ArrayList<>();
		uat = new ArrayList<>();
		done = new ArrayList<>();
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

	public Collection<SubtaskDTO> getTodo()
	{
		return todo;
	}

	public void setTodo(Collection<SubtaskDTO> todo)
	{
		this.todo = todo;
	}

	public Collection<SubtaskDTO> getDoing()
	{
		return doing;
	}

	public void setDoing(Collection<SubtaskDTO> doing)
	{
		this.doing = doing;
	}

	public Collection<SubtaskDTO> getUat()
	{
		return uat;
	}

	public void setUat(Collection<SubtaskDTO> uat)
	{
		this.uat = uat;
	}

	public Collection<SubtaskDTO> getDone()
	{
		return done;
	}

	public void setDone(Collection<SubtaskDTO> done)
	{
		this.done = done;
	}

}
