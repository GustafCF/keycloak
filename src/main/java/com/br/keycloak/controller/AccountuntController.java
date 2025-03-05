package com.br.keycloak.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.keycloak.domain.Account;
import com.br.keycloak.service.AccountService;

@RestController
@RequestMapping(value = "/account")
public class AccountuntController {

    private final AccountService service;
    
    public AccountuntController (AccountService service){
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Account>> findAll(){
        List<Account> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id){
        Account account = service.findbyId(id);
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/insert/{id}")
    public ResponseEntity<Account> insert(@PathVariable Long account){
        Account obj = service.insert(account);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
