package net.raven.scrum.ui.rest.scrum;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.rest.dto.scrum.ScrumboardDTO;
import net.raven.scrum.ui.service.scrum.ScrumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/project/")
public class ProjectScrumboardDataProvider
{

	@Autowired
	private ScrumService scrumService;

	public ProjectScrumboardDataProvider()
	{

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idProject}/scrumboard/active")
	public Response getActualSprintData(@PathParam("idProject") Long idProject)
	{
		ScrumboardDTO dto = scrumService.prepareDataForScrumboard(idProject);
		return Response.status(Status.OK).entity(dto).build();
	}
}
