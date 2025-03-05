package com.br.keycloak.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ACCOUNT")
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number" , unique = true,  nullable = false)
    private String accountNumber;
    @Column(nullable = false, columnDefinition = "DECIMAL(19,2) DEFAULT 0.00")
    private BigDecimal balance;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime created;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "account", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transaction = new ArrayList<>();

    public Account() {
    }

    public Account(String accountNumber, BigDecimal balance, LocalDateTime created, User user) {
        this.accountNumber = UUID.randomUUID().toString();
        this.balance = BigDecimal.ZERO;
        this.created = LocalDateTime.now();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void deposit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void withdraw(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public void transfer(Account account, Account destinationAccount, BigDecimal value) {
        if (this.balance.compareTo(value) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para transferência.");
        }
        account.withdraw(value);
        destinationAccount.deposit(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
