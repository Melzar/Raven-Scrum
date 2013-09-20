package net.raven.scrum.ui.service.account;

public interface AccountValidationService
{
	public boolean validateLogin(String login);

	public boolean validateEmail(String email);

	public boolean validatePassword(String password);

	// public boolean validateCity(String city);

	// TODO more specific validation for adress, city etc if needed
}
