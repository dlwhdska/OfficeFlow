package com.of.stuff.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.of.stuff.entity.Stuff;

public interface StuffRepository extends JpaRepository<Stuff, Long> {

	Optional<Stuff> findByName(String name);
}
