package com.br.keycloak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.keycloak.domain.User;
import com.br.keycloak.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User findById(Long id){
        Optional<User> user = userRepo.findById(id);
        return  user.get();
    }

    public User insert(User entity){
        return userRepo.save(entity);
    }

    public void delete(Long id){
        userRepo.deleteById(id);
    }

    public User update(Long id, User obj){
        User user = userRepo.getReferenceById(id);
        updateData(user, obj);
        return userRepo.save(user);
    }

    private void updateData(User obj, User entity) {
        if(entity.getName() != null && !entity.getName().isBlank()){
            obj.setName(entity.getName());
        }
        if(entity.getUsername() != null && !entity.getUsername().isBlank()){
            obj.setUsername(entity.getUsername());
        }
        if(entity.getEmail() != null && !entity.getEmail().isBlank()){
            obj.setEmail(entity.getEmail());
        }
        if(entity.getCpf() != null && !entity.getCpf().isBlank()){
            obj.setCpf(entity.getCpf());
        }
        if(entity.getAge() != null) {
            obj.setAge(entity.getAge());
        }
    }
}
