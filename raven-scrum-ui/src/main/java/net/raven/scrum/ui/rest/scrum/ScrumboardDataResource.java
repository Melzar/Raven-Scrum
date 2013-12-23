package net.raven.scrum.ui.rest.scrum;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.annotations.logger.Log;
import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;
import net.raven.scrum.ui.service.scrum.ScrumService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/scrumboard")
public class ScrumboardDataResource
{

	@Autowired
	private ScrumService scrumService;

	@Log
	private Logger log;

	public ScrumboardDataResource()
	{

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/active")
	public Response getActualSprintData(@QueryParam("project") Long idProject)
	{
		ProjectDTO dto;
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

}
