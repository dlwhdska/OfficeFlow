package com.of.meeting.dto;

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
public class MeetingroomDetailDTO {

	private Long id;
	private String name;
	private int maxNum;
	private int monitor;
	private int projector;
	private int board;
	private String location;
	
}
