package com.college.yi.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {
	
	@GetMapping("/login")
	public String loginViewPage() {
		return "login";
	}

}
