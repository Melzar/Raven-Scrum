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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import net.raven.scrum.core.enumeration.scrum.ScrumTaskState;
import net.raven.scrum.core.enumeration.scrum.ScrumTaskType;

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

	private ScrumTaskType type;

	private ScrumTaskState state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_parent", insertable = false, updatable = false)
	private ScrumTask parent;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_parent")
	private Set<ScrumTask> subtasks;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tasks")
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

	public ScrumTaskType getType()
	{
		return type;
	}

	public void setType(ScrumTaskType type)
	{
		this.type = type;
	}

	public ScrumTaskState getState()
	{
		return state;
	}

	public void setState(ScrumTaskState state)
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

}
