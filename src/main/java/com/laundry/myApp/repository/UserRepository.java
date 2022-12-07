package com.laundry.myApp.repository;

import com.laundry.myApp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.laundry.myApp.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	// List<User> findAllByRoles(List<Role> roles);

}
