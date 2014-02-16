package net.raven.scrum.ui.rest.project;

import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.repository.ScrumProjectRepository;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/project/validate")
public class ProjectValidation
{

	@Autowired
	private ScrumProjectRepository projectRepository;

	public ProjectValidation()
	{

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/title")
	public Response validateTitle(ProjectDTO dto)
	{
		if (dto != null && dto.getTitle() != null)
		{
			return Response
					.status(Status.OK)
					.entity(Collections.singletonMap("unique",
							projectRepository.isProjectTitleUnique(dto
									.getTitle()))).build();
		}
		return Response.status(Status.OK)
				.entity(Collections.singletonMap("unique", false)).build();
	}
}
