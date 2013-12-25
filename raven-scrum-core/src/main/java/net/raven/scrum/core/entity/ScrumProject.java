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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import net.raven.scrum.core.enumeration.scrum.ProjectStatus;

@Entity
public class ScrumProject
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long idProject;

	private String title;

	private String description;

	private ProjectStatus status;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_project")
	private Set<ScrumSprint> sprints;

	// @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade = CascadeType.ALL)
	private Set<ScrumUserProjectRole> userprojectrole;

	// private Set<ScrumUser> projectUsers;

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

	public ProjectStatus getStatus()
	{
		return status;
	}

	public void setStatus(ProjectStatus status)
	{
		this.status = status;
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

	public Set<ScrumUserProjectRole> getUserProjectRole()
	{
		return userprojectrole;
	}

	public void setUserProjectRole(Set<ScrumUserProjectRole> userprojectrole)
	{
		this.userprojectrole = userprojectrole;
	}

}
