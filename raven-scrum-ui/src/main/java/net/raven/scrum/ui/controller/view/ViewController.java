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

	@RequestMapping("/register")
	public String prepareRegister(Model model)
	{
		return "ScrumRegister";
	}

	@RequestMapping("/account/dashboard")
	@PreAuthorize("isAuthenticated()")
	public String prepareScrumDashboard(Model model)
	{
		return "ScrumDashboard";
	}

	@RequestMapping("/account/delete")
	public String prepareDeleteAccount(Model model)
	{
		return "ScrumDeleteAccount";
	}

	@RequestMapping("/account/edit")
	@PreAuthorize("isAuthenticated()")
	public String prepareEditAccount(Model model)
	{
		return "ScrumEditAccount";
	}

	@RequestMapping("/scrumboard")
	// @PreAuthorize("isAuthenticated()")
	public String prepareScrumBoard(Model model)
	{
		return "ScrumBoard";
	}
}
