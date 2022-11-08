package com.example.demo.repositories;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email);
}
