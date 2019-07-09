package com.heanoria.tests.banking.core.domain.operations;

import com.heanoria.tests.banking.core.domain.data.OperationDetails;

import java.time.LocalDateTime;

import static com.heanoria.tests.banking.core.domain.data.OperationDetails.depositOperationBuilder;

public class DepositOperationAction implements OperationAction {
    @Override
    public OperationDetails perform(int amount, int lastBalance) {
        return depositOperationBuilder().amount(amount).date(LocalDateTime.now())
                .balance(lastBalance + amount).build();
    }
}
