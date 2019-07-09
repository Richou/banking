package com.heanoria.tests.banking.core.domain;

import com.heanoria.tests.banking.core.domain.data.OperationActionType;
import com.heanoria.tests.banking.core.domain.data.OperationDetails;
import com.heanoria.tests.banking.core.domain.ports.OperationDetailsPort;
import com.heanoria.tests.banking.core.infra.adapters.InMemoryOperationDetailsAdapter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.heanoria.tests.banking.core.domain.data.OperationActionType.DEPOSIT;
import static com.heanoria.tests.banking.core.domain.data.OperationActionType.WITHDRAWAL;

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
        Assertions.assertThat(this.account.consult()).extracting("operation").containsExactly(DEPOSIT);
    }

    @Test
    @DisplayName("Should deposit several times")
    public void shouldDepositSeveralTimes() {
        this.account.deposit(1440);
        this.account.deposit(8945);
        this.account.deposit(9000);
        List<OperationDetails> consultation = this.account.consult();
        Assertions.assertThat(consultation).isNotEmpty();
        Assertions.assertThat(consultation).hasSize(3);
        Assertions.assertThat(consultation).extracting("operation").containsExactly(DEPOSIT, DEPOSIT, DEPOSIT);
        Assertions.assertThat(this.account.getLastBalance()).isEqualTo(19385);
    }

    @Test
    @DisplayName("Should withdraw several times")
    public void shouldWithdrawSeveralTimes() {
        // Deposit one time
        this.account.deposit(3000);
        // Then make withdraw
        this.account.withdrawal(1000);
        this.account.withdrawal(1552);
        this.account.withdrawal(400);
        List<OperationDetails> consultation = this.account.consult();
        Assertions.assertThat(consultation).isNotEmpty();
        Assertions.assertThat(consultation).hasSize(4);
        Assertions.assertThat(consultation).extracting("operation").containsExactly(DEPOSIT, WITHDRAWAL, WITHDRAWAL, WITHDRAWAL);
        Assertions.assertThat(this.account.getLastBalance()).isEqualTo(48);
    }

    @Test
    @DisplayName("Should withdraw and deposit several times")
    public void shouldWithdrawAndDespositSeveralTimes() {
        this.account.deposit(3000);
        this.account.withdrawal(1000);
        this.account.deposit(2000);
        this.account.withdrawal(450);
        this.account.withdrawal(900);
        this.account.deposit(1599);
        this.account.withdrawal(400);
        List<OperationDetails> consultation = this.account.consult();
        Assertions.assertThat(consultation).isNotEmpty();
        Assertions.assertThat(consultation).hasSize(7);
        Assertions.assertThat(consultation).extracting("operation").containsExactly(DEPOSIT, WITHDRAWAL, DEPOSIT, WITHDRAWAL, WITHDRAWAL, DEPOSIT, WITHDRAWAL);
        Assertions.assertThat(this.account.getLastBalance()).isEqualTo(3849);
    }
}
