package net.raven.scrum.core.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class ScrumColor
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long idColor;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_color")
	private Set<ScrumEpic> epics;

	private String code;

	public ScrumColor()
	{

	}

	public long getIdColor()
	{
		return idColor;
	}

	public void setIdColor(long idColor)
	{
		this.idColor = idColor;
	}

	public Set<ScrumEpic> getEpics()
	{
		return epics;
	}

	public void setEpics(Set<ScrumEpic> epics)
	{
		this.epics = epics;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

}
