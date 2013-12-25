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

}
