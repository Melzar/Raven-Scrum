package net.raven.scrum.ui.rest.scrum;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.annotations.logger.Log;
import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.ScrumboardDTO;
import net.raven.scrum.ui.service.scrum.ScrumService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/project/")
public class ProjectScrumboardDataResource
{

	@Autowired
	private ScrumService scrumService;

	@Log
	private Logger log;

	public ProjectScrumboardDataResource()
	{

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idProject}/scrumboard/active")
	public Response getActualSprintData(@PathParam("idProject") Long idProject)
	{
		ScrumboardDTO dto;
		try
		{
			dto = scrumService.prepareDataForScrumboard(idProject);
			return Response.status(Status.OK).entity(dto).build();
		} catch (ScrumException e)
		{
			log.error("Failed to get actual sprint data", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idProject}/users")
	public Response getProjectUsers(@PathParam("idProject") Long idProject)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumService.getProjectUsers(idProject)).build();
		} catch (ScrumException e)
		{
			log.error("Failed to get users for project with id " + idProject, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
