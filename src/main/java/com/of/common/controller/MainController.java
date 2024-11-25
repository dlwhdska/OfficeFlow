package com.of.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.of.attendance.dto.AttendanceDTO;
import com.of.attendance.service.AttendanceService;
import com.of.member.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final AttendanceService attendanceService;

	@GetMapping("/")
	public String login() {
		return "member/login";
	}
	
    
    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
        
        if (loginMember == null) {
            return "redirect:/";
        }
        
        AttendanceDTO todayAttendanceStartTime = attendanceService.getStartTime(loginMember);
        AttendanceDTO todayAttendanceEndTime = attendanceService.getEndTime(loginMember);
        model.addAttribute("todayAttendanceStartTime", todayAttendanceStartTime);
        model.addAttribute("todayAttendanceEndTime", todayAttendanceEndTime);
        
        return "main";
    }	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}
