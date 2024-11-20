package com.of.stuff.dto;

import java.time.LocalDate;

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
public class StuffReqDTO {

	private Long id;
	private String memberName;
	private String stuffName;
	private LocalDate reqDate;
	private int quantity;
	private int status;
	private String purpose;
	private String reject;
	
}
