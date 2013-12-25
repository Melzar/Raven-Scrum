package net.raven.scrum.core.rest.dto.user;

import net.raven.scrum.core.enumeration.scrum.ProjectRole;

public class ScrumUserDTO
{
	private long id;

	private String name;

	private String surname;

	private String login;

	private ProjectRole role;

	private String tag;

	public ScrumUserDTO()
	{

	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
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

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public ProjectRole getRole()
	{
		return role;
	}

	public void setRole(ProjectRole role)
	{
		this.role = role;
	}

}
