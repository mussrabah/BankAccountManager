package org.muss_amine_coding.bank_account_manager.remote;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String insufficientBalance) {
        super(insufficientBalance);
    }
}
