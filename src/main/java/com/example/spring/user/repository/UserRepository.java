package com.example.spring.user.repository;

import com.example.spring.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
