package com.of.stuff.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.of.member.entity.Member;
import com.of.stuff.entity.StuffReq;
import com.of.stuff.entity.StuffReqStatus;

public interface StuffReqRepository extends JpaRepository<StuffReq, Long> {

	Page<StuffReq> findByMember(Member member, Pageable pageable);
	
	Page<StuffReq> findByMemberAndStatus(Member member, StuffReqStatus status, Pageable pageable);
	
	Page<StuffReq> findByMemberAndStatusAndReqDateBetween(Member member, StuffReqStatus status, LocalDate startDate, LocalDate endDate, Pageable pageable);
	
	Page<StuffReq> findByMemberAndReqDateBetween( Member member, LocalDate startDate, LocalDate endDate, Pageable pageable );
}
