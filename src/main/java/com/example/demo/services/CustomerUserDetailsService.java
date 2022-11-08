package com.example.demo.services;

import com.example.demo.CustomUserDetails;
import com.example.demo.CustomerUserDetails;
import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.repositories.ICustomerRepository;
import com.example.demo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private ICustomerRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = repo.findByEmail(username);
        if(customer == null){
            throw new UsernameNotFoundException("No customer found for the given email!");
        }
        return new CustomerUserDetails(customer);
    }
}
