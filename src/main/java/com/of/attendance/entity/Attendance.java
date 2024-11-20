package com.of.attendance.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.of.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

	// 근태번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 사원번호
	@ManyToOne
	@JoinColumn(nullable = false)
	private Member memberId;
	
	// 일자
	private LocalDate attendanceDate;
	
	// 출근시간
	private LocalTime startTime;
	
	// 퇴근시간
	private LocalTime endTime;
	
	// 상태(0 : 출석, 1 : 퇴근, 2 : 지각, 3 : 결근, 4 : 병가, 5 : 휴가)
	private int status;
	
}
