package com.heanoria.tests.banking.core.domain.operations;

import com.heanoria.tests.banking.core.domain.data.OperationActionType;
import com.heanoria.tests.banking.core.domain.data.OperationDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class WithdrawalOperationActionTest {

    private OperationAction operationAction;

    @BeforeEach
    public void onInit() {
        this.operationAction = new WithdrawalOperationAction();
    }

    @Test
    @DisplayName("Should withdraw money correctly")
    public void shouldWithdrawMoneyCorrectly() {
        LocalDateTime started = LocalDateTime.now();
        OperationDetails perform = this.operationAction.perform(2000, 3556);
        LocalDateTime ended = LocalDateTime.now();
        Assertions.assertThat(perform).isNotNull();
        Assertions.assertThat(perform.getAmount()).isEqualTo(2000);
        Assertions.assertThat(perform.getBalance()).isEqualTo(1556);
        Assertions.assertThat(perform.getDate()).isBetween(started, ended);
        Assertions.assertThat(perform.getOperation()).isEqualTo(OperationActionType.WITHDRAWAL);
    }


    @Test
    @DisplayName("Should withdraw money correctly, even under zero")
    public void shouldWithdrawMoneyCorrectlyWithNegative() {
        LocalDateTime started = LocalDateTime.now();
        OperationDetails perform = this.operationAction.perform(2000, 1556);
        LocalDateTime ended = LocalDateTime.now();
        Assertions.assertThat(perform).isNotNull();
        Assertions.assertThat(perform.getAmount()).isEqualTo(2000);
        Assertions.assertThat(perform.getBalance()).isEqualTo(-444);
        Assertions.assertThat(perform.getDate()).isBetween(started, ended);
        Assertions.assertThat(perform.getOperation()).isEqualTo(OperationActionType.WITHDRAWAL);
    }
}
