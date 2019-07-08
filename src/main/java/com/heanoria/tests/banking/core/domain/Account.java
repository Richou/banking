package com.heanoria.tests.banking.core.domain;

import com.heanoria.tests.banking.core.domain.data.OperationDetails;
import com.heanoria.tests.banking.core.domain.operations.OperationActionType;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final List<OperationDetails> operationDetails;

    public Account() {
        this(new ArrayList<>());
    }

    public Account(List<OperationDetails> operationDetails) {
        this.operationDetails = new ArrayList<>(operationDetails);
    }

    public double getLastBalance() {
        return operationDetails.stream().map(OperationDetails::getBalance)
                .reduce((first, second) -> second).orElse(0d);
    }

    public void deposit(double amount) {
        this.operationAction(OperationActionType.DEPOSIT, amount);
    }

    public void withdrawal(double amount) {
        this.operationAction(OperationActionType.WITHDRAWAL, amount);
    }

    public List<OperationDetails> consult() {
        return new ArrayList<>(this.operationDetails);
    }

    private void operationAction(OperationActionType type, double amount) {
        OperationDetails depositOperation = type.get().perform(amount, getLastBalance());
        this.operationDetails.add(depositOperation);
    }
}
