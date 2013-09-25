package net.raven.scrum.ui.service.account;

import java.math.BigInteger;
import java.security.SecureRandom;

import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.exception.AccountException;
import net.raven.scrum.core.repository.ScrumUserRepository;
import net.raven.scrum.core.security.enumeration.ShadowFlag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService
{

	@Autowired
	private ScrumUserRepository scrumUserRepository;

	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	@Autowired
	private AccountValidationService accountValidationService;

	public AccountServiceImpl()
	{

	}

	public ScrumUser changeEmail(String login, String newemail)
			throws AccountException
	{
		if (accountValidationService.validateEmail(newemail)
				&& scrumUserRepository.getUserByEmail(newemail) == null)
		{
			ScrumUser user = scrumUserRepository.getUserByLogin(login);
			if (user != null)
			{
				user.setEmail(newemail);
				scrumUserRepository.save(user);
			}
			return user;
		} else
		{
			return null;
		}
	}

	public ScrumUser changePassword(String login, String newpassword)
			throws AccountException
	{
		ScrumUser user = scrumUserRepository.getUserByLogin(login);
		if (accountValidationService.validatePassword(newpassword)
				&& user != null && !user.getLogin().equals(newpassword))
		{
			user.setPassword(passwordEncoder.encodePassword(newpassword, null));
			scrumUserRepository.save(user);
			return user;
		} else
		{
			return null;
		}
	}

	public ScrumUser blockUserAccount(String login, ShadowFlag reason)
			throws AccountException
	{
		ScrumUser user = scrumUserRepository.getUserByLogin(login);
		SecureRandom random = new SecureRandom();
		user.setPassword(passwordEncoder.encodePassword(new BigInteger(130,
				random).toString(32), null));
		user.setShadowFlag(reason);
		scrumUserRepository.save(user);
		return user;
	}
}
