package com.of.attendance.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.of.attendance.dto.AttendanceDTO;
import com.of.attendance.entity.Attendance;
import com.of.attendance.entity.AttendanceStatus;
import com.of.attendance.repository.AttendanceRepository;
import com.of.member.dto.MemberDTO;
import com.of.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {

	private final AttendanceRepository attendanceRepository;
	
	private String convertStatus(AttendanceStatus status) {
        return switch (status) {
            case CLOCK_IN -> "출근";
            case CLOCK_OUT -> "퇴근";
            case OFF_WORK -> "조퇴";
            case LATE -> "지각";
            case ABSENT -> "결근";
            case SICK_LEAVE -> "병가";
            case VACATION -> "휴가";
        };
    }
	
	private Member convertToMember(MemberDTO memberDTO) {
        return Member.builder()
                .id(memberDTO.getId())
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .position(memberDTO.getPosition())
                .tel(memberDTO.getTel())
                .role(memberDTO.getRole())
                .build();
    }
	
	private AttendanceDTO convertToAttendanceDTO(Attendance attendance) {
        return AttendanceDTO.builder()
                .id(attendance.getId())
                .memberName(attendance.getMember().getName())
                .attendanceDate(attendance.getAttendanceDate())
                .startTime(attendance.getStartTime())
                .endTime(attendance.getEndTime())
                .statusText(convertStatus(attendance.getStatus()))
                .build();
    }
	
	public Page<AttendanceDTO> getAttendanceList(MemberDTO loginMemberDTO, Pageable pageable) {
		
		Member loginMember = convertToMember(loginMemberDTO);
		
		Page<Attendance> attendancePage = attendanceRepository.findByMember(loginMember, pageable);
		
		return attendancePage.map(attendance -> AttendanceDTO.builder()
				.id(attendance.getId())
				.memberName(attendance.getMember().getName())
				.attendanceDate(attendance.getAttendanceDate())
				.startTime(attendance.getStartTime())
				.endTime(attendance.getEndTime())
				.statusText(convertStatus(attendance.getStatus()))
				.build());
	}
	
	public AttendanceDTO insertClockIn(MemberDTO loginMemberDTO) {
		
		Member loginMember = convertToMember(loginMemberDTO);
		
		LocalDate today = LocalDate.now();
		Optional<Attendance> existingAttendance = attendanceRepository.findByMemberAndAttendanceDate(loginMember, today);
		
		if (existingAttendance.map(attendance -> attendance.getStartTime()).isPresent()) {
	        throw new RuntimeException("이미 오늘의 출근 기록이 존재합니다.");
	    }
		
		Attendance attendance = new Attendance();
		attendance.setMember(loginMember);
		attendance.setAttendanceDate(today);
		attendance.setStartTime(LocalTime.now());
		
		if (LocalTime.now().isAfter(LocalTime.of(9, 0))) {
			attendance.setStatus(AttendanceStatus.LATE);
		} else {
			attendance.setStatus(AttendanceStatus.CLOCK_IN);
		}
		
		attendance = attendanceRepository.save(attendance);
		
		return convertToAttendanceDTO(attendance);
	}
	
	public AttendanceDTO getStartTime(MemberDTO loginMemberDTO) {
		
		Member loginMember = convertToMember(loginMemberDTO);
		
		LocalDate today = LocalDate.now();
		Optional<Attendance> existingAttendance = attendanceRepository.findByMemberAndAttendanceDate(loginMember, today);
		
		if (existingAttendance.isPresent()) {
	        Attendance attendance = existingAttendance.get();
	        return AttendanceDTO.builder()
	                .startTime(attendance.getStartTime())
	                .build();
	    }
		
		return null;
	}
	
	public AttendanceDTO insertClockOut(MemberDTO loginMemberDTO) {
	
		Member loginMember = convertToMember(loginMemberDTO);
		
		LocalDate today = LocalDate.now();
		Optional<Attendance> existingAttendance = attendanceRepository.findByMemberAndAttendanceDate(loginMember, today);
		
		Attendance attendance = existingAttendance.orElseThrow(() ->  new RuntimeException("오늘 출근이 없습니다. 먼저 출근을 해주세요."));
		
		if (attendance.getEndTime() != null) {
	        throw new RuntimeException("이미 퇴근 처리되었습니다.");
	    }
		
		attendance.setEndTime(LocalTime.now());
		
		if (LocalTime.now().isAfter(LocalTime.of(18, 0))) {
			attendance.setStatus(AttendanceStatus.CLOCK_OUT);
		} else {
			attendance.setStatus(AttendanceStatus.OFF_WORK);
		}
		
		attendance = attendanceRepository.save(attendance);
		
		return convertToAttendanceDTO(attendance);
	}
	
	public AttendanceDTO getEndTime(MemberDTO loginMemberDTO) {

		Member loginMember = convertToMember(loginMemberDTO);
		
		LocalDate today = LocalDate.now();
		Optional<Attendance> existingAttendance = attendanceRepository.findByMemberAndAttendanceDate(loginMember, today);
		
		if (existingAttendance.isPresent()) {
	        Attendance attendance = existingAttendance.get();
	        return AttendanceDTO.builder()
	                .endTime(attendance.getEndTime())
	                .build();
	    }
		
		return null;
	}
	
	public Page<AttendanceDTO> getAttendanceListByMonth(MemberDTO loginMemberDTO, int month, Pageable pageable) {
	    Member loginMember = convertToMember(loginMemberDTO);
	    
	    LocalDate now = LocalDate.now();
	    LocalDate startOfMonth = LocalDate.of(now.getYear(), month, 1);
	    LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);
	    
	    Page<Attendance> attendancePage = attendanceRepository.findByMemberAndAttendanceDateBetween(
	        loginMember, startOfMonth, endOfMonth, pageable);
	        
	    return attendancePage.map(attendance -> AttendanceDTO.builder()
	            .id(attendance.getId())
	            .memberName(attendance.getMember().getName())
	            .attendanceDate(attendance.getAttendanceDate())
	            .startTime(attendance.getStartTime())
	            .endTime(attendance.getEndTime())
	            .statusText(convertStatus(attendance.getStatus()))
	            .build());
	}
}
