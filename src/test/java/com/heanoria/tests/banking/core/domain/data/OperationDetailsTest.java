package com.heanoria.tests.banking.core.domain.data;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OperationDetailsTest {

    @Test
    @DisplayName("Should Deposit builder correct")
    public void shouldDepositBuilderCorrect() {
        OperationDetails built = OperationDetails.depositOperationBuilder().build();
        Assertions.assertThat(built.getOperation()).isEqualTo(OperationActionType.DEPOSIT);
    }

    @Test
    @DisplayName("Should Withdraw builder correct")
    public void shouldWithdrawBuilderCorrect() {
        OperationDetails built = OperationDetails.withdrawalOperation().build();
        Assertions.assertThat(built.getOperation()).isEqualTo(OperationActionType.WITHDRAWAL);
    }
}
