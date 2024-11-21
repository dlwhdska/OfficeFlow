package com.of.member.dto;

import com.of.member.entity.Role;

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
public class MemberDTO {

	private Long id;
	private String departmentName;
	private String name;
	private String email;
	private String password;
	private String position;
	private String tel;
	private Role role;
	
}
