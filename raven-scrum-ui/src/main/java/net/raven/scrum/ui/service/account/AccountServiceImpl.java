package net.raven.scrum.ui.service.account;

import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.exception.AccountException;
import net.raven.scrum.core.repository.ScrumUserRepository;

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

	public AccountServiceImpl()
	{

	}

	public ScrumUser changeEmail(String login, String newemail)
			throws AccountException
	{
		ScrumUser user = scrumUserRepository.getUserByLogin(login);
		user.setEmail(newemail);
		scrumUserRepository.save(user);
		return user;
	}

	public ScrumUser changePassword(String login, String newpassword)
			throws AccountException
	{
		ScrumUser user = scrumUserRepository.getUserByLogin(login);
		user.setPassword(passwordEncoder.encodePassword(newpassword, null));
		scrumUserRepository.save(user);
		return user;
	}

}
