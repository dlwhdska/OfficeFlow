package com.of.meeting.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class MeetingroomReservationDTO {

	private Long id;
	private String meetingroomName;
	private String memberName;
	private LocalDate meetingDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String purpose;
	
}
