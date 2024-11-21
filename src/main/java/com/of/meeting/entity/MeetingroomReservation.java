package com.of.meeting.entity;

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
public class MeetingroomReservation {

	// 회의실예약번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 회의실번호
	@ManyToOne
	@JoinColumn(nullable = false, name = "meetingroomId")
	private MeetingroomDetail meetingroom;

	// 사원번호(예약자)
	@ManyToOne
	@JoinColumn(nullable = false, name = "memberId")
	private Member member;

	// 회의날짜
	private LocalDate meetingDate;

	// 시작시간
	private LocalTime startTime;

	// 종료시간
	private LocalTime endTime;

	// 대여목적
	private String purpose;

}
