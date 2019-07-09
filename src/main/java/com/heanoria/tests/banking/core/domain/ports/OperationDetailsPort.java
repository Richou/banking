package com.heanoria.tests.banking.core.domain.ports;

import com.heanoria.tests.banking.core.domain.data.OperationDetails;

import java.util.List;

public interface OperationDetailsPort {
    List<OperationDetails> findAllOperations();
    void saveOperation(OperationDetails operationDetails);
}
