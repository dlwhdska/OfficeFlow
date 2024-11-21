package com.of.attendance.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.of.attendance.dto.AttendanceDTO;
import com.of.attendance.entity.Attendance;
import com.of.attendance.repository.AttendanceRepository;
import com.of.member.dto.MemberDTO;
import com.of.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {

	private final AttendanceRepository attendanceRepository;
	
	public Page<AttendanceDTO> getAttendanceList(MemberDTO loginMemberDTO, Pageable pageable) {
		
		Member loginMember = Member.builder()
				.id(loginMemberDTO.getId())
				.name(loginMemberDTO.getName())
				.email(loginMemberDTO.getEmail())
				.password(loginMemberDTO.getPassword())
				.position(loginMemberDTO.getPosition())
				.tel(loginMemberDTO.getTel())
				.role(loginMemberDTO.getRole())
				.build();
		
		Page<Attendance> attendancePage = attendanceRepository.findByMember(loginMember, pageable);
		
		return attendancePage.map(attendance -> AttendanceDTO.builder()
				.id(attendance.getId())
				.memberName(attendance.getMember().getName())
				.attendanceDate(attendance.getAttendanceDate())
				.startTime(attendance.getStartTime())
				.endTime(attendance.getEndTime())
				.status(attendance.getStatus())
				.build());
	}
}
