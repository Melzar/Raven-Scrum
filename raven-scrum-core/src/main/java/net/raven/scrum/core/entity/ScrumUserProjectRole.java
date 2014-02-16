package net.raven.scrum.core.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
@AssociationOverrides({
		@AssociationOverride(name = "pk.project", joinColumns = @JoinColumn(name = "id_project")),
		@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "id_user")),
		@AssociationOverride(name = "pk.role", joinColumns = @JoinColumn(name = "id_role")) })
public class ScrumUserProjectRole
{
	@EmbeddedId
	private ScrumUserProjectRoleId pk = new ScrumUserProjectRoleId();

	public ScrumUserProjectRole()
	{
	}

	public ScrumUserProjectRoleId getPk()
	{
		return pk;
	}

	public void setPk(ScrumUserProjectRoleId pk)
	{
		this.pk = pk;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ScrumUserProjectRole that = (ScrumUserProjectRole) o;
		if (pk != null ? !pk.equals(that.pk) : that.pk != null)
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result;
		result = (pk != null ? pk.hashCode() : 0);
		return result;
	}

}
