package com.of.stuff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.of.stuff.entity.StuffReq;

public interface StuffReqRepository extends JpaRepository<StuffReq, Long> {

}
