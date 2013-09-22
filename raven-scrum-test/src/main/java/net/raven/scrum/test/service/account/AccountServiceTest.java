package net.raven.scrum.test.service.account;

import java.util.Collection;

import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.exception.AccountException;
import net.raven.scrum.test.TestDataProvider;
import net.raven.scrum.test.TestDataProviderImpl;
import net.raven.scrum.ui.service.account.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@TestExecutionListeners({ TransactionalTestExecutionListener.class })
@Transactional
public class AccountServiceTest extends AbstractTestNGSpringContextTests
{

	@Autowired
	private AccountService accountService;

	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	private TestDataProvider testDataProvider = new TestDataProviderImpl();

	public AccountServiceTest()
	{

	}

	@DataProvider(name = "validChangePasswordProvider")
	public Object[][] validChangePasswordProvider()
	{
		testDataProvider
				.loadDataByLine("AccountServiceTest/validChangePassword.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@DataProvider(name = "invalidChangePasswordProvider")
	public Object[][] invalidChangePasswordProvider()
	{
		testDataProvider
				.loadDataByLine("AccountServiceTest/invalidChangePassword.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@DataProvider(name = "validChangeEmailProvider")
	public Object[][] validChangeEmailProvider()
	{
		testDataProvider
				.loadDataByLine("AccountServiceTest/validChangeEmail.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@DataProvider(name = "invalidChangeEmailProvider")
	public Object[][] invalidChangeEmailProvider()
	{
		testDataProvider
				.loadDataByLine("AccountServiceTest/invalidChangeEmail.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@Test(dataProvider = "validChangePasswordProvider")
	public void testValidChangePassword(Collection<String> data)
	{
		String[] values;
		try
		{
			for (String line : data)
			{
				values = line.split(":");
				ScrumUser testuser = accountService.changePassword(values[0],
						values[1]);
				Assert.assertTrue(passwordEncoder.isPasswordValid(
						testuser.getPassword(), values[1], null));

			}
		} catch (AccountException e)
		{
			Assert.fail("There should be no exception in valid password provider");
		}
	}

	@Test(dataProvider = "invalidChangePasswordProvider")
	public void testInValidChangePassword(Collection<String> data)
	{
		try
		{
			String[] values;
			for (String line : data)
			{
				values = line.split(":");
				Assert.assertNull(
						accountService.changePassword(values[0], values[1]),
						"Should be null");
			}

		} catch (AccountException e)
		{
			Assert.fail("No exception expected");
		}
	}

	@Test(dataProvider = "validChangeEmailProvider")
	public void testValidChangeEmail(Collection<String> data)
	{
		try
		{
			String[] values;
			for (String line : data)
			{
				values = line.split(":");
				ScrumUser testuser = accountService.changeEmail(values[0],
						values[1]);
				Assert.assertSame(values[1], testuser.getEmail(),
						"Provided mail should be like object email");
			}
		} catch (AccountException ex)
		{
			Assert.fail("No exception expected here");
		}
	}

	@Test(dataProvider = "invalidChangeEmailProvider")
	public void testInvalidChangeEmail(Collection<String> data)
	{
		try
		{
			String[] values;
			for (String line : data)
			{
				System.out.println(line);
				values = line.split(":");
				ScrumUser testuser = accountService.changeEmail(values[0],
						values[1]);
				Assert.assertNull(testuser, "Invalid data");
			}
		} catch (AccountException ex)
		{
			Assert.fail("No exception expected here");
		}
	}
}
