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
	private final Pattern EMAIL_PATTERN = Pattern.compile("^.+$");
	private final Pattern PASSWORD_PATTERN = Pattern.compile("^.+$");

	public AccountValidationServiceImpl()
	{

	}

	public boolean validateLogin(String login)
	{
		return login.matches(LOGIN_PATTERN.pattern());
	}

	public boolean validateEmail(String email)
	{
		return email.matches(EMAIL_PATTERN.pattern());
	}

	public boolean validateEmails(String email1, String email2)
	{
		return email1.matches(EMAIL_PATTERN.pattern()) && email1.equals(email2);
	}

	public boolean validatePasswords(String password1, String password2)
	{
		return password2.matches(PASSWORD_PATTERN.pattern())
				&& password2.equals(password1);
	}

	public boolean validateCity(String city)
	{
		return city.matches(LOGIN_PATTERN.pattern());
	}
}
