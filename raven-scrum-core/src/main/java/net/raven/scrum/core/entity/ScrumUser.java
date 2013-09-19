package net.raven.scrum.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import net.raven.scrum.core.security.enumeration.ShadowFlag;

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

	public ScrumUser()
	{

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
	};

	@Transient
	public boolean hasShadowFlag()
	{
		return this.shadowFlag != ShadowFlag.OK;
	}

}
