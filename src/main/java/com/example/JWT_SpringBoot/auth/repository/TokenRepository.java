package com.example.JWT_SpringBoot.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository  extends JpaRepository<Token, Integer> {
}
