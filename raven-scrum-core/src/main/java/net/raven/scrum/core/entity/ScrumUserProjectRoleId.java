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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ScrumUserProjectRoleId that = (ScrumUserProjectRoleId) o;

		if (project != null ? !project.equals(that.project)
				: that.project != null)
			return false;
		if (user != null ? !user.equals(that.user) : that.user != null)
			return false;
		if (role != null ? !role.equals(that.role) : that.role != null)
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result;
		result = (project != null ? project.hashCode() : 0);
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		return result;
	}

}
