package net.raven.scrum.test.controller.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import net.raven.scrum.core.enumeration.security.ShadowFlag;
import net.raven.scrum.core.repository.ScrumUserRepository;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@TestExecutionListeners({ TransactionalTestExecutionListener.class })
@Transactional
public class DeleteAccountTest extends AbstractTestNGSpringContextTests
{

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ScrumUserRepository userRepository;

	private MockMvc mockmvc;

	private Authentication authentication;

	public DeleteAccountTest()
	{

	}

	@BeforeClass
	public void initialize()
	{
		mockmvc = MockMvcBuilders.webAppContextSetup(wac).build();
		List<GrantedAuthority> grantedauthorities = Collections.emptyList();
		authentication = new UsernamePasswordAuthenticationToken("test", null,
				grantedauthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Test
	public void testInvalidDeleteAccount()
	{
		try
		{
			mockmvc.perform(
					post("/account/delete").contentType(
							MediaType.APPLICATION_FORM_URLENCODED).param(
							"password", "testowy")).andExpect(status().isOk())
					.andExpect(jsonPath("$.success").value(false));
		} catch (Exception e)
		{
			e.printStackTrace();
			Assert.fail("No exception expected here");
		}
	}

	@Test
	public void testValidDeleteAccount()
	{
		try
		{
			mockmvc.perform(
					post("/account/delete").contentType(
							MediaType.APPLICATION_FORM_URLENCODED).param(
							"password", "test")).andExpect(status().isOk())
					.andExpect(jsonPath("$.success").value(true));
			Assert.assertEquals(userRepository.getUserByLogin("test")
					.getShadowFlag().compareTo(ShadowFlag.DELETED_BY_USER), 0,
					"There should be DELETED shadow flag");
		} catch (Exception e)
		{
			e.printStackTrace();
			Assert.fail("No exception expected here");
		}
	}
}
