package net.raven.scrum.ui.rest.scrum;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.exception.ScrumException;
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
}
