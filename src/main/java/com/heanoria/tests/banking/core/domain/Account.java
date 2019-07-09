package com.heanoria.tests.banking.core.domain;

import com.heanoria.tests.banking.core.domain.data.OperationActionType;
import com.heanoria.tests.banking.core.domain.data.OperationDetails;
import com.heanoria.tests.banking.core.domain.ports.OperationDetailsPort;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final OperationDetailsPort operationDetails;

    public Account(OperationDetailsPort operationDetails) {
        this.operationDetails = operationDetails;
    }

    public int getLastBalance() {
        return this.operationDetails.findAllOperations().stream().map(OperationDetails::getBalance)
                .reduce((first, second) -> second).orElse(0);
    }

    public void deposit(int amount) {
        this.operationAction(OperationActionType.DEPOSIT, amount);
    }

    public void withdrawal(int amount) {
        this.operationAction(OperationActionType.WITHDRAWAL, amount);
    }

    public List<OperationDetails> consult() {
        return new ArrayList<>(this.operationDetails.findAllOperations());
    }

    private void operationAction(OperationActionType type, int amount) {
        OperationDetails depositOperation = type.get().perform(amount, getLastBalance());
        this.operationDetails.saveOperation(depositOperation);
    }
}
