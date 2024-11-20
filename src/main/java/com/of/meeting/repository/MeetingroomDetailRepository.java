package com.of.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.of.meeting.entity.MeetingroomDetail;

@Repository
public interface MeetingroomDetailRepository extends JpaRepository<MeetingroomDetail, Long> {

}
