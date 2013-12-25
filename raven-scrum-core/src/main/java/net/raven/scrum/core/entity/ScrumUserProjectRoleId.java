package net.raven.scrum.core.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ScrumUserProjectRoleId implements Serializable
{

	@ManyToOne
	private ScrumProject project;

	@ManyToOne
	private ScrumUser user;

	@ManyToOne
	private ScrumRole role;

	public ScrumUserProjectRoleId()
	{

	}

	public ScrumProject getProject()
	{
		return project;
	}

	public void setProject(ScrumProject project)
	{
		this.project = project;
	}

	public ScrumUser getUser()
	{
		return user;
	}

	public void setUser(ScrumUser user)
	{
		this.user = user;
	}

	public ScrumRole getRole()
	{
		return role;
	}

	public void setRole(ScrumRole role)
	{
		this.role = role;
	}

}
