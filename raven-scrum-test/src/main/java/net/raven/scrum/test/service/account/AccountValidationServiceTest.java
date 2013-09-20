package net.raven.scrum.test.service.account;

import java.util.Collection;

import net.raven.scrum.test.TestDataProvider;
import net.raven.scrum.test.TestDataProviderImpl;
import net.raven.scrum.ui.service.account.AccountValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-test.xml" })
public class AccountValidationServiceTest extends
		AbstractTestNGSpringContextTests
{
	@Autowired
	private AccountValidationService accountValidationService;

	private TestDataProvider testDataProvider = new TestDataProviderImpl();

	@DataProvider(name = "invalidLoginData")
	public Object[][] provideInvalidLoginData()
	{
		testDataProvider
				.loadDataByLine("AccountValidationServiceTest/InvalidLoginData.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@DataProvider(name = "validLoginData")
	public Object[][] provideValidLoginData()
	{
		testDataProvider
				.loadDataByLine("AccountValidationServiceTest/ValidLoginData.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@DataProvider(name = "validEmailData")
	public Object[][] provideValidEmailData()
	{
		testDataProvider
				.loadDataByLine("AccountValidationServiceTest/ValidEmailData.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@DataProvider(name = "invalidEmailData")
	public Object[][] provideInvalidEmailData()
	{
		testDataProvider
				.loadDataByLine("AccountValidationServiceTest/InvalidEmailData.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@DataProvider(name = "validPasswordData")
	public Object[][] provideValidPasswordData()
	{
		testDataProvider
				.loadDataByLine("AccountValidationServiceTest/ValidPasswordData.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@DataProvider(name = "invalidPasswordData")
	public Object[][] provideInvalidPasswordData()
	{
		testDataProvider
				.loadDataByLine("AccountValidationServiceTest/InvalidPasswordData.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@Test(dataProvider = "invalidLoginData")
	public void testInvalidLogin(Collection<String> data)
	{
		for (String testlogin : data)
		{
			Assert.assertFalse(
					accountValidationService.validateLogin(testlogin),
					"Invalid login");
		}
	}

	@Test(dataProvider = "validLoginData")
	public void testValidLogin(Collection<String> data)
	{
		for (String testlogin : data)
		{
			Assert.assertTrue(
					accountValidationService.validateLogin(testlogin),
					"Valid login");
		}
	}

	@Test(dataProvider = "invalidEmailData")
	public void testInvalidEmail(Collection<String> data)
	{
		for (String testemail : data)
		{
			Assert.assertFalse(
					accountValidationService.validateEmail(testemail),
					"Invalid email");
		}
	}

	@Test(dataProvider = "validEmailData")
	public void testValidEmail(Collection<String> data)
	{
		for (String testemail : data)
		{
			Assert.assertTrue(
					accountValidationService.validateEmail(testemail),
					"Valid email");
		}
	}

	@Test(dataProvider = "validPasswordData")
	public void testValidPassword(Collection<String> data)
	{
		for (String testpassword : data)
		{
			Assert.assertTrue(
					accountValidationService.validatePassword(testpassword),
					"Valid password");
		}
	}

	@Test(dataProvider = "invalidPasswordData")
	public void testInvalidPassword(Collection<String> data)
	{
		for (String testpassword : data)
		{
			Assert.assertFalse(
					accountValidationService.validatePassword(testpassword),
					"Invalid password");
		}
	}
}
