package com.of.stuff.entity;

import java.time.LocalDate;

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
	
	// 요청상태(0 : 승인대기, 1 : 승인, 2 : 반려)
	private int status;
	
	// 신청사유
	private String purpose;
	
	// 반려사유
	private String reject;
	
}
