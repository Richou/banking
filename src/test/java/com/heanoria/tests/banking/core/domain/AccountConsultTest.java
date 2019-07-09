package com.heanoria.tests.banking.core.domain;

import com.heanoria.tests.banking.core.domain.data.OperationActionType;
import com.heanoria.tests.banking.core.domain.ports.OperationDetailsPort;
import com.heanoria.tests.banking.core.infra.adapters.InMemoryOperationDetailsAdapter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountConsultTest {

    private Account account;

    @BeforeEach
    public void onInit() {
        OperationDetailsPort adapter = new InMemoryOperationDetailsAdapter();
        this.account = new Account(adapter);
    }

    @Test
    @DisplayName("Should create with no operation")
    public void shouldCreateWithNoOperation() {
        Assertions.assertThat(this.account.consult()).isEmpty();
    }

    @Test
    @DisplayName("Should deposit only one time")
    public void shouldDepositOnlyOneTime() {
        this.account.deposit(4200);
        Assertions.assertThat(this.account.consult()).isNotEmpty();
        Assertions.assertThat(this.account.consult()).extracting("operation").containsExactly(OperationActionType.DEPOSIT);
    }

    @Test
    @DisplayName("Should deposit several times")
    public void shouldDepositSeveralTimes() {
        this.account.deposit(1440);
        this.account.deposit(8945);
        this.account.deposit(9000);
        Assertions.assertThat(this.account.consult()).isNotEmpty();
        Assertions.assertThat(this.account.getLastBalance()).isEqualTo(19385);
    }
}
