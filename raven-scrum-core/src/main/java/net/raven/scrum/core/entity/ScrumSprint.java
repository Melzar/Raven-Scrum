package net.raven.scrum.core.entity;

import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import net.raven.scrum.core.enumeration.scrum.SprintStatus;

@Entity
public class ScrumSprint
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long idSprint;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_project")
	private ScrumProject project;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "sprints", cascade = CascadeType.REMOVE)
	@OrderBy("idTask")
	private Set<ScrumTask> tasks;

	private SprintStatus status;

	public ScrumSprint()
	{

	}

	public long getIdSprint()
	{
		return idSprint;
	}

	public void setIdSprint(long idSprint)
	{
		this.idSprint = idSprint;
	}

	public ScrumProject getProject()
	{
		return project;
	}

	public void setProject(ScrumProject project)
	{
		this.project = project;
	}

	public Set<ScrumTask> getTasks()
	{
		return tasks;
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

	public void setTasks(Set<ScrumTask> tasks)
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

	@Override
	@Transient
	public String toString()
	{
		return "";
	}
}
