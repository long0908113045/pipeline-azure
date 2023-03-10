package com.example.spring.User.Repository;

import com.example.spring.User.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
