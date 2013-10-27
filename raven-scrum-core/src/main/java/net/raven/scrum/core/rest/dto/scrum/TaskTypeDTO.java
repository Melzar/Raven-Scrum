package net.raven.scrum.core.rest.dto.scrum;

import net.raven.scrum.core.enumeration.scrum.TaskType;

public class TaskTypeDTO
{
	private int id;

	private TaskType type;

	private String tag;

	public TaskTypeDTO()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public TaskType getType()
	{
		return type;
	}

	public void setType(TaskType type)
	{
		this.type = type;
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

}
