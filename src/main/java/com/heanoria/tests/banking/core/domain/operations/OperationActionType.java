package com.heanoria.tests.banking.core.domain.operations;

import java.util.function.Supplier;

public enum OperationActionType {
    DEPOSIT(DepositOperationAction::new),
    WITHDRAWAL(WithdrawalOperationAction::new);

    private final Supplier<OperationAction> operationActionSupplier;

    OperationActionType(Supplier<OperationAction> operationActionSupplier) {
        this.operationActionSupplier = operationActionSupplier;
    }

    public OperationAction get() {
        return operationActionSupplier.get();
    }
}
