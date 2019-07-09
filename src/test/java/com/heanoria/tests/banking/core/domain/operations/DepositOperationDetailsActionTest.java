package com.heanoria.tests.banking.core.domain.operations;

import com.heanoria.tests.banking.core.domain.data.OperationActionType;
import com.heanoria.tests.banking.core.domain.data.OperationDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DepositOperationDetailsActionTest {

    private OperationAction operationAction;

    @BeforeEach
    public void onInit() {
        this.operationAction = new DepositOperationAction();
    }

    @Test
    @DisplayName("Should save money correctly")
    public void shouldSaveMoneyCorrectly() {
        LocalDateTime started = LocalDateTime.now();
        OperationDetails perform = this.operationAction.perform(20.0, 35.56);
        LocalDateTime ended = LocalDateTime.now();
        Assertions.assertThat(perform).isNotNull();
        Assertions.assertThat(perform.getAmount()).isEqualTo(20.0);
        Assertions.assertThat(perform.getBalance()).isEqualTo(55.56);
        Assertions.assertThat(perform.getDate()).isBetween(started, ended);
        Assertions.assertThat(perform.getOperation()).isEqualTo(OperationActionType.DEPOSIT);
    }
}
