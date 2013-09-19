package net.raven.scrum.core.rest.security.authentication;

import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.repository.ScrumUserRepository;
import net.raven.scrum.core.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/authentication")
public class CredentialsValidator
{
	@Autowired
	private ScrumUserRepository scrumUserRepository;

	@Autowired
	private UserService userService;

	public CredentialsValidator()
	{

	}

	@POST
	@Path("/credentials")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateCredentials(@FormParam("login") String login,
			@FormParam("password") String password)
	{
		try
		{
			ScrumUser su = scrumUserRepository.getUserByLogin(login);
			if (userService.checkUserPassword(password, su.getPassword()))
			{
				return (su.hasShadowFlag()) ? Response
						.status(Status.UNAUTHORIZED)
						.entity(Collections.singletonMap("flag", su
								.getShadowFlag().ordinal())).build() : Response
						.status(Status.OK).build();
			}
			return Response.status(Status.UNAUTHORIZED).build();
		} catch (Exception ex)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
