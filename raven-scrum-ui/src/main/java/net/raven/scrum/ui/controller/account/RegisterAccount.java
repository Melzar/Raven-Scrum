package net.raven.scrum.ui.controller.account;

import java.util.Collections;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import net.raven.scrum.core.annotations.logger.Log;
import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.repository.ScrumUserRepository;
import net.raven.scrum.core.security.enumeration.ShadowFlag;
import net.raven.scrum.ui.service.account.AccountValidationService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterAccount
{
	@Autowired
	private ScrumUserRepository userRepository;

	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	@Log
	private Logger logger;

	@Autowired
	private AccountValidationService accountValidationService;

	public RegisterAccount()
	{

	}

	@RequestMapping(value = "/account/registration", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Map<String, ? extends Object> registerAccount(
			@FormParam("login") String login, @FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("passwordRepeat") String passwordrepeat)
	{
		try
		{
			if ((accountValidationService.validateLogin(login) && userRepository
					.isLoginUnique(login))
					&& (accountValidationService.validateEmail(email) && userRepository
							.isEmailUnique(email))
					&& (accountValidationService.validatePassword(password)
							&& password.equals(passwordrepeat) && !password
								.equals(login)))
			{
				ScrumUser scrumUser = new ScrumUser();
				scrumUser.setLogin(login);
				scrumUser.setPassword(passwordEncoder.encodePassword(
						passwordrepeat, null));
				scrumUser.setEmail(email);
				scrumUser.setShadowFlag(ShadowFlag.UNCONFIRMED);
				userRepository.save(scrumUser);
				return Collections.singletonMap("success", true);
			}
		} catch (Exception ex)
		{
			return Collections.singletonMap("success", false);
		}
		return Collections.singletonMap("success", false);
	}
}
