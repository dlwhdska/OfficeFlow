package com.of.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}
