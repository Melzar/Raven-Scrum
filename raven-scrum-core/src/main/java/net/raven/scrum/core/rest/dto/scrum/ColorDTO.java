package net.raven.scrum.core.rest.dto.scrum;

import java.util.Collection;

public class ColorDTO
{
	private long idColor;

	private Collection<EpicDTO> epics;

	private String code;

	public ColorDTO()
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

	public Collection<EpicDTO> getEpics()
	{
		return epics;
	}

	public void setEpics(Collection<EpicDTO> epics)
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
