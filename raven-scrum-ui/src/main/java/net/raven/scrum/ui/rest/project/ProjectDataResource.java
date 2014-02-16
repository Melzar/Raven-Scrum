package net.raven.scrum.ui.rest.project;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.annotations.logger.Log;
import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;
import net.raven.scrum.ui.service.project.ProjectService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/project")
public class ProjectDataResource
{
	@Autowired
	private ProjectService projectService;

	@Log
	private Logger log;

	public ProjectDataResource()
	{

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response createProject(ProjectDTO dto)
	{
		try
		{
			if (dto.getStatus() != null && dto.getTitle() != null
					&& !dto.getTitle().isEmpty())
			{
				return Response.status(Status.OK)
						.entity(projectService.crateProject(dto)).build();
			}
			return Response
					.status(Status.CONFLICT)
					.header("X-Status-Reason",
							"Request couldn't pass internal validation, check your data")
					.build();
		} catch (ScrumException e)
		{
			// add logger
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users")
	public Response getProjectUsers(@QueryParam("project") Long idProject)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(projectService.getProjectUsers(idProject)).build();
		} catch (ScrumException e)
		{
			log.error("Failed to get users for project with id " + idProject, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getProjectList()
	{
		try
		{
			return Response.status(Status.OK)
					.entity(projectService.getProjectList()).build();
		} catch (ScrumException e)
		{
			log.error("Failed to get project list", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list/user")
	public Response getProjectListForUser(@QueryParam("login") String login)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(projectService.getProjectListForUser(login))
					.build();
		} catch (ScrumException e)
		{
			log.error("Failed to get project list for user " + login, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
