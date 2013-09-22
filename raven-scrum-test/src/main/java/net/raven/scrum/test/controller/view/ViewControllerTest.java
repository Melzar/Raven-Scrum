package net.raven.scrum.test.controller.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;

import net.raven.scrum.test.TestDataProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ViewControllerTest extends AbstractTestNGSpringContextTests
{
	@Autowired
	private WebApplicationContext wep;

	@Autowired
	private TestDataProvider testDataProvider;

	private MockMvc mvc;

	private Authentication authentication;

	@BeforeClass
	public void initialize()
	{
		mvc = MockMvcBuilders.webAppContextSetup(wep).build();
		List<GrantedAuthority> grantedAuthorities = Collections.emptyList();
		authentication = new UsernamePasswordAuthenticationToken("test", null,
				grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@DataProvider
	public Object[][] loadViewData()
	{
		testDataProvider.loadDataByLine("ViewControllerTest/basicViewData.txt");
		return new Object[][] { { testDataProvider.getDataByLine() } };
	}

	@Test(dataProvider = "loadViewData")
	public void testViewController(Collection<String> data)
	{
		String[] values;
		for (String line : data)
		{
			try
			{
				values = line.split(":");
				mvc.perform(get(values[0])).andExpect(status().isOk())
						.andExpect(view().name(values[1]));
			} catch (Exception e)
			{
				if (!(e instanceof ServletException)
						|| (e instanceof NestedServletException))
				{
					Assert.fail("Only ServletException expected connected with view resolving");
				}
			}
		}
	}
}
