package com.br.keycloak.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.br.keycloak.domain.Transaction;
import com.br.keycloak.domain.enums.TransactionType;
import com.br.keycloak.repository.AccountRepository;
import com.br.keycloak.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public TransactionService(TransactionRepository transactionRepo, AccountRepository accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    @Transactional
    public Transaction deposit(Long id, BigDecimal value){
        var account = accountRepo.getReferenceById(id);
        account.deposit(value);
        Transaction transaction = new Transaction();
        transaction.setAmount(value);
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAccount(account);
        transactionRepo.save(transaction);
        account.getTransaction().add(transaction);
        accountRepo.save(account);
        return transaction;
    }

    @Transactional
    public Transaction withdraw(Long id, BigDecimal value) {
        var account = accountRepo.getReferenceById(id);
        account.withdraw(value);
        Transaction transaction = new Transaction();
        transaction.setAmount(value);
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setAccount(account);
        transactionRepo.save(transaction);
        account.getTransaction().add(transaction);
        accountRepo.save(account);
        return transaction;
    }

    @Transactional
    public Transaction transfer(Long account, Long destinationAccount, BigDecimal value) {
        var dAccount = accountRepo.getReferenceById(destinationAccount);
        var envAccount = accountRepo.getReferenceById(account);
        dAccount.transfer(dAccount, dAccount, value);
        Transaction transaction = new Transaction();
        transaction.setAmount(value);
        transaction.setType(TransactionType.TRANSFER);
        transaction.setAccount(dAccount);
        transactionRepo.save(transaction);
        dAccount.getTransaction().add(transaction);
        accountRepo.saveAll(Arrays.asList(dAccount, envAccount));
        return transaction;
    }
}
