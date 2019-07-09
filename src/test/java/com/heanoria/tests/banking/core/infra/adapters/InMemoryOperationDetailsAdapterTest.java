package com.heanoria.tests.banking.infra.adapters;

import com.heanoria.tests.banking.core.domain.data.OperationDetails;
import com.heanoria.tests.banking.core.infra.adapters.InMemoryOperationDetailsAdapter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.heanoria.tests.banking.core.domain.data.OperationActionType.DEPOSIT;

public class InMemoryOperationDetailsAdapterTest {

    private InMemoryOperationDetailsAdapter adapter;

    @BeforeEach
    public void onInit() {
        this.adapter = new InMemoryOperationDetailsAdapter();
    }

    @Test
    @DisplayName("Should Operation details list be empty after instantiate")
    public void shouldOperationEmpty() {
        Assertions.assertThat(this.adapter.findAllOperations()).isEmpty();
    }

    @Test
    @DisplayName("Should save operation correctly")
    public void shouldAddOperation() {
        this.adapter.saveOperation(OperationDetails.builder().operation(DEPOSIT).build());
        List<OperationDetails> operations = this.adapter.findAllOperations();
        Assertions.assertThat(operations).isNotEmpty();
        Assertions.assertThat(operations).hasSize(1);
        Assertions.assertThat(operations).extracting("operation").containsExactly(DEPOSIT);
    }
}
