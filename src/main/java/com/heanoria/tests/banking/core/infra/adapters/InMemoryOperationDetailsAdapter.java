package com.heanoria.tests.banking.core.infra.adapters;

import com.heanoria.tests.banking.core.domain.data.OperationDetails;
import com.heanoria.tests.banking.core.domain.ports.OperationDetailsPort;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOperationDetailsAdapter implements OperationDetailsPort {

    private final List<OperationDetails> operationDetails;

    public InMemoryOperationDetailsAdapter() {
        this(new ArrayList<>());
    }

    public InMemoryOperationDetailsAdapter(List<OperationDetails> operationDetails) {
        this.operationDetails = operationDetails;
    }

    @Override
    public List<OperationDetails> findAllOperations() {
        return new ArrayList<>(operationDetails);
    }

    @Override
    public void saveOperation(OperationDetails operationDetails) {
        this.operationDetails.add(operationDetails);
    }
}
