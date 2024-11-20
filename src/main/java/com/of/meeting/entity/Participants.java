package com.of.meeting.entity;

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
public class Participants {

	// 참여자번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 사원번호
	@ManyToOne
	@JoinColumn(nullable = false)
	private Member memberId;

	// 회의실예약번호
	@ManyToOne
	@JoinColumn(nullable = false)
	private MeetingroomReservation meetingId;
	
}
