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

import net.raven.scrum.core.repository.ScrumUserRepository;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@Path("/validate")
public class AccountValidation
{
	@Autowired
	private ScrumUserRepository scrumUserRepository;

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
		response.put("unique", true);
		try
		{
			String login = new JSONObject(json).getString("login");
			if (scrumUserRepository.getUserByLogin(login) != null)
			{
				response.put("unique", false);
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
		response.put("unique", true);
		try
		{
			String email = new JSONObject(json).getString("email");
			if (scrumUserRepository.getUserByEmail(email) != null)
			{
				response.put("unique", false);
			}
		} catch (JSONException ex)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).entity(response).build();
	}
}
