package com.example.spring.admin.repository;

import com.example.spring.admin.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
}
