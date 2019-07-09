package com.heanoria.tests.banking.core.domain.data;

import com.heanoria.tests.banking.core.domain.operations.DepositOperationAction;
import com.heanoria.tests.banking.core.domain.operations.OperationAction;
import com.heanoria.tests.banking.core.domain.operations.WithdrawalOperationAction;

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
