package net.raven.scrum.ui.service.account;

import java.util.Collection;

import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.enumeration.security.ShadowFlag;
import net.raven.scrum.core.exception.AccountException;
import net.raven.scrum.core.rest.dto.user.ScrumUserDTO;

public interface AccountService
{
	public ScrumUser changeEmail(String login, String newemail)
			throws AccountException;

	public ScrumUser changePassword(String login, String newpassword)
			throws AccountException;

	public ScrumUser blockUserAccount(String login, ShadowFlag reason)
			throws AccountException;

	public Collection<ScrumUserDTO> getActiveAccountList()
			throws AccountException;
}
