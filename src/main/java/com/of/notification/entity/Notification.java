package com.of.notification.entity;

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
public class Notification {
	
	// 알림번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 사원번호
	@ManyToOne
	@JoinColumn(nullable = false)
	private Member memberId;
	
	// 알림타입(NOTICE, MEETING, STUFF)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NotificationType type;
	
	// 알림내용
	private String content;
	
	// 알림생성일시
	private LocalDate createdAt;
	
	// 상태(0 : 확인, -1 : 미확인)
	private int status;
	
}
