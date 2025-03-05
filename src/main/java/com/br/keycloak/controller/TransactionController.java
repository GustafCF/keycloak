package com.br.keycloak.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.keycloak.domain.Transaction;
import com.br.keycloak.service.TransactionService;

@RestController
@RequestMapping("/ts")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    } 

    @PostMapping("/deposit/{id}")
    public ResponseEntity<Transaction> deposit(@PathVariable Long id, @RequestParam("amount") BigDecimal value){
        var deposit = service.deposit(id, value);
        return ResponseEntity.ok().body(deposit);
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<Transaction> withdraw(@PathVariable Long id, @RequestParam("amount") BigDecimal value) {
        var withdraw = service.withdraw(id, value);
        return ResponseEntity.ok().body(withdraw);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestParam("account") Long account, @RequestParam("destinationAccount") Long destinationAccount, @RequestParam("amount") BigDecimal value) {
            var withdraw = service.transfer(account, destinationAccount, value);
            return ResponseEntity.ok().body(withdraw);
        }

}
