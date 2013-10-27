package net.raven.scrum.core.exception;

public class ScrumException extends Exception
{
	public ScrumException()
	{
		super();
	}

	public ScrumException(String message)
	{
		super(message);
	}

	public ScrumException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ScrumException(Throwable cause)
	{
		super(cause);
	}
}
