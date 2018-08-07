package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Moderator;

public interface ModeratorRepository extends JpaRepository<Moderator, Long>{

}
