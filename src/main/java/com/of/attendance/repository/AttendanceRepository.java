package com.of.attendance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.of.attendance.entity.Attendance;
import com.of.member.entity.Member;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	Page<Attendance> findByMember(Member member, Pageable pageable);
	
}
