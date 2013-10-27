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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class ScrumProject
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long idProject;

	private String title;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private ScrumUser manager;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_project")
	private Set<ScrumSprint> sprints;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
	private Set<ScrumUser> projectUsers;

	public ScrumProject()
	{

	}

	public long getIdProject()
	{
		return idProject;
	}

	public void setIdProject(long idProject)
	{
		this.idProject = idProject;
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

	public ScrumUser getManager()
	{
		return manager;
	}

	public void setManager(ScrumUser manager)
	{
		this.manager = manager;
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

	public Set<ScrumUser> getProjectUsers()
	{
		return projectUsers;
	}

	public void setProjectUsers(Set<ScrumUser> projectUsers)
	{
		this.projectUsers = projectUsers;
	}

}
