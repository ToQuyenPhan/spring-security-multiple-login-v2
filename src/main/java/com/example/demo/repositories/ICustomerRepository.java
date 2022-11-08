package com.example.demo.repositories;

import com.example.demo.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Integer> {
    public Customer findByEmail(String email);
}
