package com.of.notice.dto;

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
public class NoticeDTO {
	
	private Long id;
	private String memberName;
	private String title;
	private String content;
	private LocalDate regDate;
	
}