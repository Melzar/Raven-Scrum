package net.raven.scrum.core.rest.dto.scrum;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EpicDTO
{
	private long idEpic;

	private String epicName;

	private ProjectDTO project;

	private ColorDTO color;

	public EpicDTO()
	{

	}

	public long getIdEpic()
	{
		return idEpic;
	}

	public void setIdEpic(long idEpic)
	{
		this.idEpic = idEpic;
	}

	public String getEpicName()
	{
		return epicName;
	}

	public void setEpicName(String epicName)
	{
		this.epicName = epicName;
	}

	public ProjectDTO getProject()
	{
		return project;
	}

	public void setProject(ProjectDTO project)
	{
		this.project = project;
	}

	public ColorDTO getColor()
	{
		return color;
	}

	public void setColor(ColorDTO color)
	{
		this.color = color;
	}

}
