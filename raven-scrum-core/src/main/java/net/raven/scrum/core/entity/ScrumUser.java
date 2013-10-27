package net.raven.scrum.core.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import net.raven.scrum.core.enumeration.security.ShadowFlag;

@Entity
public class ScrumUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long idUser;

	private String login;

	private String password;

	private String email;

	private String name;

	private String surname;

	private ShadowFlag shadowFlag;

	private String lastLogin;

	private long loginCount;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Set<ScrumTask> tasks;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "scrum_users_in_project", joinColumns = { @JoinColumn(name = "id_user", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_project", nullable = false, updatable = false) })
	private Set<ScrumProject> projects;

	public ScrumUser()
	{

	}

	public long getIdUser()
	{
		return idUser;
	}

	public void setIdUser(long idUser)
	{
		this.idUser = idUser;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public ShadowFlag getShadowFlag()
	{
		return shadowFlag;
	}

	public void setShadowFlag(ShadowFlag shadowFlag)
	{
		this.shadowFlag = shadowFlag;
	}

	public String getLastLogin()
	{
		return lastLogin;
	}

	public void setLastLogin(String lastLogin)
	{
		this.lastLogin = lastLogin;
	}

	public long getLoginCount()
	{
		return loginCount;
	}

	public void setLoginCount(long loginCount)
	{
		this.loginCount = loginCount;
	}

	public Set<ScrumTask> getTasks()
	{
		return tasks;
	}

	public void setTasks(Set<ScrumTask> tasks)
	{
		this.tasks = tasks;
	}

	public Set<ScrumProject> getProjects()
	{
		return projects;
	}

	public void setProjects(Set<ScrumProject> projects)
	{
		this.projects = projects;
	}

	@Transient
	public boolean hasShadowFlag()
	{
		return this.shadowFlag != ShadowFlag.OK;
	}

}
