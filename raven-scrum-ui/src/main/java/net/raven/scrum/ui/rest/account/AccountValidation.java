package net.raven.scrum.ui.rest.account;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.repository.ScrumUserRepository;
import net.raven.scrum.core.service.user.UserService;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@Path("/validate")
public class AccountValidation
{
	@Autowired
	private ScrumUserRepository scrumUserRepository;

	@Autowired
	private UserService userService;

	public AccountValidation()
	{

	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validateLogin(@RequestBody String json)
	{
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("unique", false);
		try
		{
			String login = new JSONObject(json).getString("login");
			if (scrumUserRepository.isLoginUnique(login))
			{
				response.put("unique", true);
			}
		} catch (JSONException ex)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@POST
	@Path("/email")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validateEmail(@RequestBody String json)
	{
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("unique", false);
		try
		{
			String email = new JSONObject(json).getString("email");
			if (scrumUserRepository.isEmailUnique(email))
			{
				response.put("unique", true);
			}
		} catch (JSONException ex)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@POST
	@Path("/password")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validatePassword(@RequestBody String json)
	{
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("credentials", false);
		try
		{
			String password = new JSONObject(json).getString("password");
			ScrumUser su = scrumUserRepository
					.getUserByLogin(SecurityContextHolder.getContext()
							.getAuthentication().getName());
			if (userService.checkUserPassword(password, su.getPassword()))
			{
				response.put("credentials", true);
			}
		} catch (JSONException ex)
		{
			return Response.status(Status.OK).entity(response).build();
		}
		return Response.status(Status.OK).entity(response).build();
	}
}
