package com.of.schedule.dto;

import java.time.LocalDateTime;

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
public class ScheduleDTO {

	private Long id;
	private String memberName;
	private String title;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
}
