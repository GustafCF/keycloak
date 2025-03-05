package com.br.keycloak.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.keycloak.domain.User;
import com.br.keycloak.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService service;
    
    public UserController (UserService service){
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/insert")
    public ResponseEntity<User> insert(@RequestBody User user){
        User obj = service.insert(user);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        User obj = service.update(id, user);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
