package com.ensah.core.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
	
	@Autowired
    PasswordEncoder passwordEncoder;

	@GetMapping("/showMyLoginPage")
	public String showLoginForm() {
	

		 
		return "loginForm";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";

	}

	@GetMapping("/student/showHome")
	public String showStudentHomePage() {

		return "student/userHomePage";

	}
	
	@GetMapping("/prof/showHome")
	public String showProfHomePage() {

		return "prof/userHomePage";

	}
	
	@GetMapping("/cadreadmin/showHome")
	public String showCadreAdminHomePage() {

		return "cadreadmin/userHomePage";

	}

	@GetMapping("/admin/showAdminHome")
	public String showAdminHome() {

		return "admin/adminHome";

	}

}
