package net.raven.scrum.ui.controller.account;

import java.util.Collections;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.enumeration.security.ShadowFlag;
import net.raven.scrum.core.exception.AccountException;
import net.raven.scrum.core.repository.ScrumUserRepository;
import net.raven.scrum.core.service.user.UserService;
import net.raven.scrum.ui.service.account.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeleteAccount
{

	@Autowired
	private ScrumUserRepository scrumUserRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	public DeleteAccount()
	{

	}

	@RequestMapping(value = "/account/delete", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Map<String, ? extends Object> deleteUserAccount(
			@FormParam("password") String password)
	{
		try
		{
			ScrumUser su = scrumUserRepository
					.getUserByLogin(SecurityContextHolder.getContext()
							.getAuthentication().getName());
			if (userService.checkUserPassword(password, su.getPassword()))
			{
				accountService.blockUserAccount(su.getLogin(),
						ShadowFlag.DELETED_BY_USER);
				SecurityContextHolder.getContext().setAuthentication(null);
				;
				return Collections.singletonMap("success", true);
			}
			return Collections.singletonMap("false", false);
		} catch (AccountException ex)
		{
			return Collections.singletonMap("false", false);
		}
	}
}
