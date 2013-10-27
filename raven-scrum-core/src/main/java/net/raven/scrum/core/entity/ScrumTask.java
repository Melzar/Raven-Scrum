package net.raven.scrum.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import net.raven.scrum.core.enumeration.scrum.TaskState;
import net.raven.scrum.core.enumeration.scrum.TaskType;

@Entity
public class ScrumTask
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long idTask;

	private String title;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private ScrumUser assigned;

	private TaskType type;

	private TaskState state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_parent", insertable = false, updatable = false)
	private ScrumTask parent;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_parent")
	@OrderBy("idTask")
	private Set<ScrumTask> subtasks;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "scrum_task_in_sprint", joinColumns = { @JoinColumn(name = "id_task", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_sprint", nullable = false, updatable = false) })
	private Set<ScrumSprint> sprints;

	public ScrumTask()
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

	public ScrumUser getAssigned()
	{
		return assigned;
	}

	public void setAssigned(ScrumUser assigned)
	{
		this.assigned = assigned;
	}

	public TaskType getType()
	{
		return type;
	}

	public void setType(TaskType type)
	{
		this.type = type;
	}

	public TaskState getState()
	{
		return state;
	}

	public void setState(TaskState state)
	{
		this.state = state;
	}

	public ScrumTask getParent()
	{
		return parent;
	}

	public void setParent(ScrumTask parent)
	{
		this.parent = parent;
	}

	public Set<ScrumTask> getSubtasks()
	{
		return subtasks;
	}

	public void setSubtasks(Set<ScrumTask> subtasks)
	{
		this.subtasks = subtasks;
	}

	public Set<ScrumSprint> getSprints()
	{
		return sprints;
	}

	public void setSprints(Set<ScrumSprint> sprints)
	{
		this.sprints = sprints;
	}

	@Override
	@Transient
	public String toString()
	{
		return "";
	}

}
