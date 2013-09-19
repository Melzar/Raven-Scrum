package net.raven.scrum.core.service.user;

public interface UserService
{
	public boolean checkUserPassword(String providedpassword,
			String hashedpassword);
}
