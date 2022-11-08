package com.example.demo.database;

import com.example.demo.Role;
import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.repositories.ICustomerRepository;
import com.example.demo.repositories.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseLoader {
    private IUserRepository userRepo;
    private ICustomerRepository customerRepo;

    public DatabaseLoader(IUserRepository userRepo, ICustomerRepository customerRepo) {
        this.userRepo = userRepo;
        this.customerRepo = customerRepo;
    }

    @Bean
    public CommandLineRunner initializeDatabase(){
        return args -> {
            User user1 = new User("quyen1230@gmail.com", "123456");
            User user2 = new User("quyenlh8888@gmail.com", "123456");
            userRepo.saveAll(List.of(user1, user2));
            Customer customer1 = new Customer("quyen1230@gmail.com", "123456", "Quyen Phan");
            Customer customer2 = new Customer("quyen12340@gmail.com", "123456", "Phan Quyen");
            customerRepo.saveAll(List.of(customer1, customer2));
            System.out.println("Database initialized.");
        };
    }
}
