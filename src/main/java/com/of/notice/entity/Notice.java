package com.of.notice.entity;

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
public class Notice {
	
	// 공지사항번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 사원번호
	@ManyToOne
	@JoinColumn(nullable = false, name = "memberId")
	private Member member;
	
	// 제목
	private String title;
	
	// 내용
	private String content;
	
	// 작성일자
	private LocalDate regDate;
	
}
