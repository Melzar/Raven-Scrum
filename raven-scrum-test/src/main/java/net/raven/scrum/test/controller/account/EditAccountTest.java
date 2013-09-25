package net.raven.scrum.test.controller.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.raven.scrum.test.TestDataProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@TestExecutionListeners({ TransactionalTestExecutionListener.class })
@Transactional
public class EditAccountTest extends AbstractTestNGSpringContextTests
{
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private TestDataProvider dataProvider;

	private MockMvc mockMvc;

	private Authentication authentication;

	public EditAccountTest()
	{

	}

	@BeforeClass
	public void initialize()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@DataProvider
	public Object[][] invalidEditAccountDataPassword()
	{
		dataProvider
				.loadDataByLine("EditAccountTest/invalidEditDataPassword.txt");
		return new Object[][] { { dataProvider.getDataByLine() } };
	}

	@AfterMethod
	public void logoutTestUser()
	{
		SecurityContextHolder.clearContext();
	}

	@Test(dataProvider = "invalidEditAccountDataPassword")
	public void testInvalidEditAccountPassword(Collection<String> data)
	{
		String[] values;
		List<GrantedAuthority> grantedAuthorities = Collections.emptyList();
		try
		{
			for (String line : data)
			{
				values = line.split(":");
				SecurityContextHolder.getContext().setAuthentication(
						new UsernamePasswordAuthenticationToken(values[2],
								null, grantedAuthorities));
				mockMvc.perform(
						post("/account/edit/password")
								.contentType(
										MediaType.APPLICATION_FORM_URLENCODED)
								.param("password", values[0])
								.param("passwordrepeat", values[1]))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.success").value(false));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			Assert.fail("No exception expected here");
		}
	}

	@Test
	public void testValidEditAccountPassword()
	{
		try
		{
			List<GrantedAuthority> grantedAuthorities = Collections.emptyList();
			authentication = new UsernamePasswordAuthenticationToken("test",
					null, grantedAuthorities);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			mockMvc.perform(
					post("/account/edit/password")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.param("password", "password")
							.param("passwordrepeat", "password"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.success").value(true));
		} catch (Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception not expected");
		}
	}

	// TODO test email edit
}
