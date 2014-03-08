package net.raven.scrum.ui.rest.scrum;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.ColorDTO;
import net.raven.scrum.core.rest.dto.scrum.EpicDTO;
import net.raven.scrum.ui.service.scrum.ScrumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/backlog")
public class BacklogDataResource
{

	@Autowired
	private ScrumService scrumservice;

	public BacklogDataResource()
	{

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/data")
	public Response getDataForSprint(@QueryParam("project") long idProject)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumservice.prepareBacklogData(idProject)).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/epic/list")
	public Response getEpicListForProject(@QueryParam("project") long project)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumservice.getProjectEpicsList(project)).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/epic/save")
	public Response addEpicToProject(EpicDTO dto)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumservice.addEpicToProject(dto)).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/colors/list")
	public Response getColorsList()
	{
		try
		{
			Collection<ColorDTO> colors = scrumservice.getAllAvailableColors();
			return Response.status(Status.OK).entity(colors).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/epic/updateColor")
	public Response updateEpicColor(EpicDTO epic)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumservice.updateEpicColor(epic)).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/epic/updateName")
	public Response updateEpicName(EpicDTO epic)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumservice.updateEpicName(epic)).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/epic/delete")
	public Response deleteEpic(@QueryParam("epic") long epic)
	{
		try
		{
			scrumservice.deleteEpic(epic);
			return Response.status(Status.OK).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.OK).build();
		}
	}
}
