package net.raven.scrum.core.exception;

public class AccountException extends Exception
{
	public AccountException()
	{
		super();
	}

	public AccountException(String message)
	{
		super(message);
	}

	public AccountException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public AccountException(Throwable cause)
	{
		super(cause);
	}
}
