package com.doctoroncall.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Role;
import com.doctoroncall.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByNameLike(String name);

	User findByEmail(String email);

	User findByConfirmationToken(String confirmationToken);

	int countByNameLike(String name);

	User findByRole(Role role);

	User findByContactNumber(String number);

	Page<User> findByNameLike(String name, Pageable pageable);

	Iterable<User> findAllByNameLikeAndRole(String string, Role role);

	Page<User> findAllByNameLikeAndRoleAndStateId(String string, Role role, Integer stateId, Pageable pageable);

}
