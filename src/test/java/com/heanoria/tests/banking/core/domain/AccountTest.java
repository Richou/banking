package com.heanoria.tests.banking.core.domain;

import com.heanoria.tests.banking.core.domain.data.OperationDetails;
import com.heanoria.tests.banking.core.domain.operations.OperationActionType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class AccountTest {

    @Test
    @DisplayName("Should new Account has no operations")
    public void shouldNewAccountHasNoOperations() {
        Account account = new Account();
        List<OperationDetails> consult = account.consult();
        Assertions.assertThat(consult).isNotNull();
        Assertions.assertThat(consult).isEmpty();
    }

    @Test
    @DisplayName("Should get last Balance returns zero with no operations")
    public void shouldBalanceReturnsZeroNoOperations() {
        Account account = new Account();
        double lastBalance = account.getLastBalance();
        Assertions.assertThat(lastBalance).isEqualTo(0d);
    }

    @Test
    @DisplayName("Should get correct last balance")
    public void shouldGetCorrectlyAccountBalance() {
        OperationDetails operation1 = OperationDetails.builder().balance(12.3).date(LocalDateTime.now())
                .operation(OperationActionType.DEPOSIT).amount(10.0).build();
        OperationDetails operation2 = OperationDetails.builder().balance(15.3).date(LocalDateTime.now())
                .operation(OperationActionType.WITHDRAWAL).amount(10.0).build();
        Account account = new Account(Arrays.asList(operation1, operation2));
        double lastBalance = account.getLastBalance();
        Assertions.assertThat(lastBalance).isEqualTo(15.3);
    }
}
