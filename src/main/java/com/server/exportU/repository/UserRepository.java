package com.server.exportU.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.exportU.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
