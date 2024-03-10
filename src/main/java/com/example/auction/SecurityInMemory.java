package com.example.auction;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
@EnableWebSecurity
public class SecurityInMemory {
    @Bean
    public UserDetailsManager userDetailsManager(){
      return new InMemoryUserDetailsManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable
        );
        return httpSecurity.build();
    }

    @Bean
    public InitializingBean initializingBean(UserDetailsManager userDetailsManager) {
        return () -> {
            System.out.println("SecurityInMemory.initializingBean - lambda");
            UserDetails user1 = User
                    .builder()
                    .passwordEncoder(password ->
                            PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .password("password")
                    .username("user")
                    .roles("USER")
                    .build();
            userDetailsManager.createUser(user1);
        };
    }
}
//    Step 2 - we can create anonymous implementation of the interface
//        return new InitializingBean() {
//            @Override
//            public void afterPropertiesSet() throws Exception {
//                System.out.println("Hello from initializer - anonymous Class");
//            }
//        };
//    }
//}
// Step 1 - create class that implements interface - old way
// class MyInitBean implements InitializingBean{
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("Hello from initializer");
//    }
//}