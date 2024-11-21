package com.of.member.service;

import org.springframework.stereotype.Service;

import com.of.member.dto.MemberDTO;
import com.of.member.entity.Member;
import com.of.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	public MemberDTO login(String email, String password) {
		
		Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("가입되지않은 이메일입니다."));
		
		if (!password.equals(member.getPassword())) {
			throw new IllegalArgumentException("잘못된 비밀번호입니다.");
		}
		
		return MemberDTO.builder()
				.id(member.getId())
				.departmentName(member.getDepartment().getName())
				.name(member.getName())
				.email(member.getEmail())
				.password(member.getPassword())
				.position(member.getPosition())
				.tel(member.getTel())
				.role(member.getRole())
				.build();
	}
}
