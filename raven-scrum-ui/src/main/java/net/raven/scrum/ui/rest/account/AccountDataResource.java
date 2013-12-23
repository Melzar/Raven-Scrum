package net.raven.scrum.ui.rest.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.exception.AccountException;
import net.raven.scrum.ui.service.account.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/account/")
public class AccountDataResource
{
	@Autowired
	private AccountService accountService;

	public AccountDataResource()
	{

	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getActiveAccountList()
	{
		try
		{
			return Response.status(Status.OK)
					.entity(accountService.getActiveAccountList()).build();
		} catch (AccountException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
