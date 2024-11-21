package com.of.attendance.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class AttendanceController {

	private final AttendanceService attendanceService;
	
	@GetMapping("/attendance/attendanceList")
	public String getAttendanceList(@PageableDefault(size = 10) Pageable pageable, Model model, HttpSession session) {
		
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		
		Page<AttendanceDTO> attendancePage = attendanceService.getAttendanceList(loginMember, pageable);
		model.addAttribute("attendancePage", attendancePage);
		
		return "attendance/attendanceList";
	}
}
