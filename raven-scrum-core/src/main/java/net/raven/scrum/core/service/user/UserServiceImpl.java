package net.raven.scrum.core.service.user;

import net.raven.scrum.core.repository.ScrumUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private ScrumUserRepository scrumUserRepository;

	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	public UserServiceImpl()
	{

	}

	@Override
	public boolean checkUserPassword(String providedpassword,
			String hashedpassword)
	{
		return passwordEncoder.isPasswordValid(hashedpassword,
				providedpassword, null);
	}
}
