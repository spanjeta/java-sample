package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
