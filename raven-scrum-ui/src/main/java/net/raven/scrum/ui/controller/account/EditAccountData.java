package net.raven.scrum.ui.controller.account;

import java.util.Collections;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import net.raven.scrum.core.annotations.logger.Log;
import net.raven.scrum.core.exception.AccountException;
import net.raven.scrum.ui.service.account.AccountService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EditAccountData
{

	@Autowired
	private AccountService accountService;

	@Log
	private Logger log;

	public EditAccountData()
	{

	}

	@RequestMapping(value = "/account/edit/email", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Map<String, ? extends Object> editEmail(@FormParam("email") String email,
			@FormParam("emailrepeat") String emailrepeat)
	{
		try
		{
			if ((email.equals(emailrepeat))
					&& SecurityContextHolder.getContext().getAuthentication()
							.isAuthenticated())
			{
				boolean result = (accountService.changeEmail(
						SecurityContextHolder.getContext().getAuthentication()
								.getName(), emailrepeat) == null) ? false
						: true;
				return Collections.singletonMap("success", result);
			}
			return Collections.singletonMap("success", false);
		} catch (AccountException e)
		{
			return Collections.singletonMap("success", false);
		}
	}

	@RequestMapping(value = "/account/edit/password", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Map<String, ? extends Object> editPassword(
			@FormParam("password") String password,
			@FormParam("passwordrepeat") String passwordrepeat)
	{
		try
		{
			if ((password.equals(passwordrepeat))
					&& SecurityContextHolder.getContext().getAuthentication()
							.isAuthenticated())
			{
				boolean result = (accountService.changePassword(
						SecurityContextHolder.getContext().getAuthentication()
								.getName(), passwordrepeat) == null) ? false
						: true;
				return Collections.singletonMap("success", result);
			}
			return Collections.singletonMap("success", false);
		} catch (AccountException e)
		{
			return Collections.singletonMap("success", false);
		}
	}
}
