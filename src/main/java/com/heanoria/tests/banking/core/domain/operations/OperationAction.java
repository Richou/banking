package com.heanoria.tests.banking.core.domain.operations;

import com.heanoria.tests.banking.core.domain.data.OperationDetails;

public interface OperationAction {
    OperationDetails perform(double amount, double lastBalance);
}
