package com.example.demo;

import com.example.demo.services.CustomUserDetailsService;
import com.example.demo.services.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class CustomerSecurityConfig {
    @Bean
    public UserDetailsService customerDetailsService(){
        return new CustomerUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder2(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider2(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerDetailsService());
        provider.setPasswordEncoder(passwordEncoder2());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider2());
        http.authorizeRequests().antMatchers("/").permitAll();
        http.antMatcher("/customer/**").authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/customer/login").usernameParameter("email").loginProcessingUrl("/customer/login")
                .defaultSuccessUrl("/customer/home").permitAll()
                .and()
                .logout().logoutUrl("/customer/logout").logoutSuccessUrl("/");
        return http.build();
    }
}
