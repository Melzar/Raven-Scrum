package net.raven.scrum.core.rest.dto.scrum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.raven.scrum.core.enumeration.scrum.TaskState;

public class TaskDTO
{
	private long id;

	private long idUser;

	private String title;

	private String description;

	private Map<TaskState, ArrayList<SubtaskDTO>> progress;

	public TaskDTO()
	{
		progress = new HashMap<TaskState, ArrayList<SubtaskDTO>>();
		progress.put(TaskState.TODO, new ArrayList<SubtaskDTO>());
		progress.put(TaskState.DOING, new ArrayList<SubtaskDTO>());
		progress.put(TaskState.UAT, new ArrayList<SubtaskDTO>());
		progress.put(TaskState.DONE, new ArrayList<SubtaskDTO>());
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
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

	public Map<TaskState, ArrayList<SubtaskDTO>> getprogress()
	{
		return progress;
	}

	public void setprogress(Map<TaskState, ArrayList<SubtaskDTO>> progress)
	{
		this.progress = progress;
	}

}
