package net.raven.scrum.test.controller.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import net.raven.scrum.test.TestDataProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@TestExecutionListeners({ TransactionalTestExecutionListener.class })
@Transactional
public class RegisterAccountTest extends AbstractTestNGSpringContextTests
{
	@Autowired
	private TestDataProvider testDataProvider;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeClass
	public void initializeMock()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@DataProvider
	public Object[][] invalidRegisterAccountData()
	{
		testDataProvider
				.loadDataByLine("RegisterAccountTest/invalidRegisterData.txt");

		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@Test(dataProvider = "invalidRegisterAccountData")
	public void testInvalidRegisterAccount(Collection<String> data)
	{
		String[] values;
		for (String line : data)
		{
			values = line.split(":");
			try
			{
				mockMvc.perform(
						post("/account/registration")
								.contentType(
										MediaType.APPLICATION_FORM_URLENCODED)
								.param("login", values[0])
								.param("email", values[1])
								.param("password", values[2])
								.param("passwordrepeat", values[3]))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.success").value(false));
			} catch (Exception e)
			{
				Assert.fail("Error not expected");
			}
		}
	}

	@Test
	public void testValidRegisterAccount()
	{
		try
		{
			mockMvc.perform(
					post("/account/registration")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.param("login", "testowy")
							.param("email", "testowy@wp.pl")
							.param("password", "test")
							.param("passwordrepeat", "test"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.success").value(true));
		} catch (Exception e)
		{
			Assert.fail("Error not expected");
		}
	}
}
