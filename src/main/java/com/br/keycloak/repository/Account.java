package com.br.keycloak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Account extends JpaRepository<Account, Long> {
}
