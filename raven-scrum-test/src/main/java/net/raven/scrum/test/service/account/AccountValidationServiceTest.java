package net.raven.scrum.test.service.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import net.raven.scrum.ui.service.account.AccountValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-test.xml" })
public class AccountValidationServiceTest extends
		AbstractTestNGSpringContextTests
{
	@Autowired
	private AccountValidationService accountValidationService;

	private Collection<String> invalidlogindata;

	private Collection<String> validlogindata;

	@BeforeMethod
	public void prepareData()
	{
		invalidlogindata = new ArrayList<>();
		validlogindata = new ArrayList<>();
		try
		{
			String line;
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(
							getClass()
									.getClassLoader()
									.getResourceAsStream(
											"AccountValidationServiceTest-LoginData.txt")));
			while ((line = bufferedreader.readLine()) != null)
			{
				if (line.startsWith("#"))
				{
					continue;
				}
				if (line.equals("<newdata>"))
				{
					break;
				}
				invalidlogindata.add(line);
			}
			while ((line = bufferedreader.readLine()) != null)
			{
				if (line.startsWith("#"))
				{
					continue;
				}
				validlogindata.add(line);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("FALED TO READ DATA FROM FILE");
		}
	}

	@Test
	public void testValidateLogin()
	{
		System.out.println(invalidlogindata);
		for (String testlogin : invalidlogindata)
		{
			Assert.assertFalse(
					accountValidationService.validateLogin(testlogin),
					"Invalid login");
		}
		System.out.println(validlogindata);
		for (String testlogin : validlogindata)
		{
			Assert.assertTrue(
					accountValidationService.validateLogin(testlogin), "Ok");
		}
		System.out.println("Test completed");
	}
}
