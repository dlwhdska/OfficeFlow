package com.of.stuff.entity;

import java.time.LocalDate;

import com.of.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class StuffReq {

	// 신청번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 사원번호
	@ManyToOne
	@JoinColumn(nullable = false)
	private Member memberId;
	
	// 비품번호
	@ManyToOne
	@JoinColumn(nullable = false)
	private Stuff stuffId;
	
	// 요청일시
	private LocalDate reqDate;
	
	// 요청 수량
	private int quantity;
	
	// 신청사유
	private String purpose;
	
	// 반려사유
	private String reject;
	
	// 요청상태(PENDING : 승인대기, APPROVED : 승인, REJECTED : 반려)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StuffReqStatus status;
	
}
