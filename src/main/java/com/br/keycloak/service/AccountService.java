package com.br.keycloak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.keycloak.domain.Account;
import com.br.keycloak.domain.User;
import com.br.keycloak.repository.AccountRepository;
import com.br.keycloak.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findbyId(Long id){
        Optional<Account> account = accountRepository.findById(id);
        return account.get();
    }

    @Transactional
    public Account insert(Long id){
        Optional<User> user = userRepository.findById(id);
        Account account = new Account();
        account.setUser(user.get());
        user.get().setAccount(account);
        userRepository.save(user.get());
        return account;
    }

    public void delete(Long id){
        accountRepository.deleteById(id);
    }

}
