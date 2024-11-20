package com.of.meeting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingroomDetail {

	// 회의실번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 회의실이름
	private String name;

	// 최대인원
	private int maxNum;

	// 모니터
	private int monitor;

	// 빔프로젝터
	private int projector;

	// 화이트보드
	private int board;

	// 위치
	private String location;

}
