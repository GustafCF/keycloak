package com.br.keycloak.config;

import com.br.keycloak.domain.User;
import com.br.keycloak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepo;

    public TestConfig(@Autowired UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepo.deleteAll();

        User u1 = new User("Gustavo", "gusta", "gu@email.com", "123456789", 24);
        userRepo.save(u1);
    }
}
