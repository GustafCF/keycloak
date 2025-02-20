package com.br.keycloak.domain.enums;

import java.util.Objects;

public enum TransactionType {

    DEPOSIT(1),
    WITHDRAWAL(2),
    TRANSFER(3);

    private Integer value;

    private TransactionType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static TransactionType valueOf(Integer value) {
        for(TransactionType type : TransactionType.values()){
            if (Objects.equals(type.getValue(), value)){
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for value " + value);
    }
}
