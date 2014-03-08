package net.raven.scrum.core.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ScrumEpic
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long idEpic;

	private String epicName;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "epics")
	private Set<ScrumTask> tasks;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_color")
	private ScrumColor color;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_project")
	private ScrumProject project;

	public ScrumEpic()
	{

	}

	public long getIdEpic()
	{
		return idEpic;
	}

	public void setIdEpic(long idEpic)
	{
		this.idEpic = idEpic;
	}

	public String getEpicName()
	{
		return epicName;
	}

	public void setEpicName(String epicName)
	{
		this.epicName = epicName;
	}

	public Set<ScrumTask> getTasks()
	{
		return tasks;
	}

	public void setTasks(Set<ScrumTask> tasks)
	{
		this.tasks = tasks;
	}

	public ScrumColor getColor()
	{
		return color;
	}

	public void setColor(ScrumColor color)
	{
		this.color = color;
	}

	public ScrumProject getProject()
	{
		return project;
	}

	public void setProject(ScrumProject project)
	{
		this.project = project;
	}

}
