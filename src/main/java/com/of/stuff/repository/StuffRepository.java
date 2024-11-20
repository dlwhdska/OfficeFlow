package com.of.stuff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.of.stuff.entity.Stuff;

public interface StuffRepository extends JpaRepository<Stuff, Long> {

}
