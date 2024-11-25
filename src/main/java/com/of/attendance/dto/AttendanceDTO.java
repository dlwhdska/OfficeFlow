package com.of.attendance.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.of.attendance.entity.AttendanceStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {

	private Long id;
	private String memberName;
	private LocalDate attendanceDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private AttendanceStatus status;
	private String statusText;
	
}