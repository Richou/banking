package com.heanoria.tests.banking.core.domain.data;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class OperationDetails {

    /**
     * The type of Operation Action from {@link OperationActionType}
     */
    private final OperationActionType operation;

    /**
     * The date when the Operation occurred
     */
    private final LocalDateTime date;

    /**
     * Amount of Operation
     */
    private final double amount;

    /**
     * The balance of the account after operation occurred
     */
    private final double balance;

    public static OperationDetailsBuilder depositOperationBuilder() {
        return OperationDetails.builder().operation(OperationActionType.DEPOSIT);
    }

    public static OperationDetailsBuilder withdrawalOperation() {
        return OperationDetails.builder().operation(OperationActionType.WITHDRAWAL);
    }
}
