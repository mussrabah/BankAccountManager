package org.muss_amine_coding.bank_account_manager.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankAccount extends Remote {
    // Method to deposit money into the account.
    void deposit(double amount) throws RemoteException;

    // Method to withdraw money from the account.
    boolean withdraw(double amount) throws RemoteException, InsufficientFundsException;

    // Method to check the current balance of the account.
    double checkBalance() throws RemoteException;

    // Method to transfer money from this account to another account.
    void transfer(String accountNumber, double amount) throws RemoteException, InsufficientFundsException;

    // Method to get the account number.
    String getAccountNumber() throws RemoteException;
    String getFullName() throws RemoteException;
}
