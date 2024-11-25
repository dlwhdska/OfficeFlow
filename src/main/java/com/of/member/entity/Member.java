package com.of.member.entity;

import com.of.department.entity.Department;

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
public class Member {

	// 사원번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 부서번호
	@ManyToOne
	@JoinColumn(nullable = false)
	private Department departmentId;
	
	// 이름
	private String name;
	
	// 이메일, 로그인ID
	private String email;
	
	// 비밀번호, 로그인PWD
	private String password;
	
	// 직급(사원, 대리, 과장, 부장, 팀장, 사장)
	private String position;
	
	// 전화번호
	private String tel;
	
}
