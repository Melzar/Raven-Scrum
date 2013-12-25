package net.raven.scrum.core.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ScrumRole
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long idRole;

	private String roleName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.role")
	private Set<ScrumUserProjectRole> userprojectrole;

	public ScrumRole()
	{

	}

	public Set<ScrumUserProjectRole> getUserprojectrole()
	{
		return userprojectrole;
	}

	public void setUserprojectrole(Set<ScrumUserProjectRole> userprojectrole)
	{
		this.userprojectrole = userprojectrole;
	}

	public long getIdRole()
	{
		return idRole;
	}

	public void setIdRole(long idRole)
	{
		this.idRole = idRole;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

}
