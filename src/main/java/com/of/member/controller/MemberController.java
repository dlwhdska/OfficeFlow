package com.of.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.of.member.dto.MemberDTO;
import com.of.member.entity.Role;
import com.of.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	@GetMapping("/member/login")
	public void getLogin() {
		
	}
	
	@PostMapping("/member/login")
	public String postLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		
		try {
			MemberDTO memberDTO = memberService.login(email, password);
			session.setAttribute("loginMember", memberDTO);
			if (Role.MEMBER.equals(memberDTO.getRole())) {
				return "redirect:/";
			} else {
				return "redirect:/admin";
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/member/login";
		}
	}
}
