package net.raven.scrum.core.rest.dto.scrum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.raven.scrum.core.enumeration.scrum.TaskState;
import net.raven.scrum.core.enumeration.scrum.TaskType;

public class TaskDTO
{

	private long id;

	private long idParent;

	private long idUser;

	private long idSprint;

	private long idProject;

	private String title;

	private String description;

	private TaskState state;

	private TaskType type;

	private Map<TaskState, ArrayList<TaskDTO>> progress;

	private List<TaskDTO> subtasksRaw;

	private boolean showChildren;

	private EpicDTO epic;

	public TaskDTO()
	{
		progress = new HashMap<TaskState, ArrayList<TaskDTO>>();
		progress.put(TaskState.TODO, new ArrayList<TaskDTO>());
		progress.put(TaskState.DOING, new ArrayList<TaskDTO>());
		progress.put(TaskState.UAT, new ArrayList<TaskDTO>());
		progress.put(TaskState.DONE, new ArrayList<TaskDTO>());
		subtasksRaw = new LinkedList<>();
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

	public long getIdParent()
	{
		return idParent;
	}

	public void setIdParent(long idParent)
	{
		this.idParent = idParent;
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

	public Map<TaskState, ArrayList<TaskDTO>> getProgress()
	{
		return progress;
	}

	public void setProgress(Map<TaskState, ArrayList<TaskDTO>> progress)
	{
		this.progress = progress;
	}

	public List<TaskDTO> getSubtasksRaw()
	{
		return subtasksRaw;
	}

	public void setSubtasksRaw(List<TaskDTO> subtasksRaw)
	{
		this.subtasksRaw = subtasksRaw;
	}

	public boolean isShowChildren()
	{
		return showChildren;
	}

	public void setShowChildren(boolean showChildren)
	{
		this.showChildren = showChildren;
	}

	public EpicDTO getEpic()
	{
		return epic;
	}

	public void setEpic(EpicDTO epic)
	{
		this.epic = epic;
	}

	public long getIdSprint()
	{
		return idSprint;
	}

	public void setIdSprint(long idSprint)
	{
		this.idSprint = idSprint;
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
