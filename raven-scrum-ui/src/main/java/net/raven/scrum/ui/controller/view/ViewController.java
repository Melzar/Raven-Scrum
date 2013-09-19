package net.raven.scrum.ui.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController
{

	@RequestMapping("/login")
	public String prepareScrumLogin(Model model)
	{
		return "ScrumLogin";
	}

	@RequestMapping("/account/edit")
	@PreAuthorize("isAuthenticated()")
	public String prepareEditAccount(Model model)
	{
		return "ScrumEditAccount";
	}

	@RequestMapping("/account/dashboard")
	@PreAuthorize("isAuthenticated()")
	public String prepareScrumDashboard(Model model)
	{
		return "ScrumDashboard";
	}

	@RequestMapping("/register")
	public String prepareRegister(Model model)
	{
		return "ScrumRegister";
	}

	@RequestMapping("/scrumboard")
	// @PreAuthorize("isAuthenticated()")
	public String prepareScrumBoard(Model model)
	{
		return "ScrumBoard";
	}
}
