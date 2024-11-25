package com.of.attendance.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String getAttendanceList(@PageableDefault(size = 10, sort = "attendanceDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(name = "month", required = false) Integer month, Model model, HttpSession session) {
		
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		
		if (loginMember == null) {
	        return "redirect:/";
	    }
		
		Page<AttendanceDTO> attendancePage = attendanceService.getAttendanceList(loginMember, pageable);
		if(month != null) {
			attendancePage = attendanceService.getAttendanceListByMonth(loginMember, month, pageable);
		} else {
			attendancePage = attendanceService.getAttendanceList(loginMember, pageable);
		}
		
		model.addAttribute("attendancePage", attendancePage);
		
		return "attendance/attendanceList";
	}
	
	@PostMapping("/attendance/clockIn")
	public ResponseEntity<AttendanceDTO> clockIn(HttpSession session) {
		
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		
		AttendanceDTO attendance = attendanceService.insertClockIn(loginMember);
		
		return ResponseEntity.ok(attendance);
	}
	
	@PostMapping("/attendance/clockOut")
	public ResponseEntity<AttendanceDTO> clockOut(HttpSession session) {
		
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		
		AttendanceDTO attendance = attendanceService.insertClockOut(loginMember);
		
		return ResponseEntity.ok(attendance);
	}
}
