package com.of.attendance.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.of.attendance.entity.Attendance;
import com.of.member.entity.Member;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	Page<Attendance> findByMember(Member member, Pageable pageable);
	
	Optional<Attendance> findByMemberAndAttendanceDate(Member member, LocalDate attendanceDate);
	
	Page<Attendance> findByMemberAndAttendanceDateBetween(Member member, LocalDate startDate, LocalDate endDate, Pageable pageable);
	
}
