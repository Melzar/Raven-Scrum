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
import javax.persistence.OneToOne;

@Entity
public class ScrumBacklog
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long idBacklog;

	@OneToOne
	@JoinColumn(name = "id_project")
	private ScrumProject project;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "backlog", cascade = CascadeType.ALL)
	private Set<ScrumTask> tasks;

	public ScrumBacklog()
	{

	}

	public long getIdBacklog()
	{
		return idBacklog;
	}

	public void setIdBacklog(long idBacklog)
	{
		this.idBacklog = idBacklog;
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

	public void setTasks(Set<ScrumTask> tasks)
	{
		this.tasks = tasks;
	}

}
