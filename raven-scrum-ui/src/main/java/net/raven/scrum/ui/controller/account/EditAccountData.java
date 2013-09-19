package net.raven.scrum.ui.controller.account;

import java.util.Collections;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import net.raven.scrum.core.annotations.logger.Log;
import net.raven.scrum.core.exception.AccountException;
import net.raven.scrum.ui.service.account.AccountService;
import net.raven.scrum.ui.service.account.AccountValidationService;

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

	@Autowired
	private AccountValidationService accountValidationService;

	@Log
	private Logger log;

	public EditAccountData()
	{

	}

	@RequestMapping(value = "/account/edit/email", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Map<String, ? extends Object> editEmail(@FormParam("email") String email,
			@FormParam("emailrepeat") String email2)
	{
		try
		{
			if (accountValidationService.validateEmails(email, email2)
					&& SecurityContextHolder.getContext().getAuthentication()
							.isAuthenticated())
			{
				accountService.changeEmail(SecurityContextHolder.getContext()
						.getAuthentication().getName(), email2);
				return Collections.singletonMap("success", true);
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
			if (accountValidationService.validatePasswords(password,
					passwordrepeat)
					&& SecurityContextHolder.getContext().getAuthentication()
							.isAuthenticated())
			{
				accountService.changePassword(SecurityContextHolder
						.getContext().getAuthentication().getName(),
						passwordrepeat);
				return Collections.singletonMap("success", true);
			}
			return Collections.singletonMap("success", false);
		} catch (AccountException e)
		{
			return Collections.singletonMap("success", false);
		}
	}
}
