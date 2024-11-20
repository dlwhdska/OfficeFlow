package com.of.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.of.meeting.entity.MeetingroomReservation;

@Repository
public interface MeetingroomReservationRepository extends JpaRepository<MeetingroomReservation, Long> {

}
