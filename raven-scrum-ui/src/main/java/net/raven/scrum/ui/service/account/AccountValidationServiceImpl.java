package net.raven.scrum.ui.service.account;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class AccountValidationServiceImpl implements AccountValidationService
{
	// @Autowired
	// private ScrumUserRepository scrumUserRepository;

	// @Log
	// private Logger log;
	private final Pattern LOGIN_PATTERN = Pattern.compile("^[\\w]+$");
	private final Pattern EMAIL_PATTERN = Pattern
			.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	private final Pattern PASSWORD_PATTERN = Pattern.compile("^.+$");

	private final int MIN_LOGIN_LENGTH = 4;
	private final int MIN_PASSWORD_LENGTH = 4;

	public AccountValidationServiceImpl()
	{

	}

	public boolean validateLogin(String login)
	{
		return login.matches(LOGIN_PATTERN.pattern())
				&& login.length() >= MIN_LOGIN_LENGTH;
	}

	public boolean validateEmail(String email)
	{
		return email.matches(EMAIL_PATTERN.pattern());
	}

	public boolean validatePassword(String password)
	{
		return password.matches(PASSWORD_PATTERN.pattern())
				&& password.length() >= MIN_PASSWORD_LENGTH;
	}

	// public boolean validateCity(String city)
	// {
	// return city.matches(LOGIN_PATTERN.pattern());
	// }
}
