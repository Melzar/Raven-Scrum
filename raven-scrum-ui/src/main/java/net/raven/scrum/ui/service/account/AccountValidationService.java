package net.raven.scrum.ui.service.account;

public interface AccountValidationService
{
	public boolean validateLogin(String login);

	public boolean validateEmail(String email);

	public boolean validateEmails(String email1, String email2);

	public boolean validatePasswords(String password1, String password2);

	public boolean validateCity(String city);

	// TODO more specific validation for adress, city etc if needed
}
