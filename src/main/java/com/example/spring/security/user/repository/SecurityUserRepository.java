package com.example.spring.security.user.repository;

import com.example.spring.security.user.model.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {
    SecurityUser findByEmail(String email);
}
