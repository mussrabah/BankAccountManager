package org.muss_amine_coding.bank_account_manager.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankAccountManager extends Remote {
    BankAccount createAccount(String firstName, String LastName) throws RemoteException;
    void closeAccount(BankAccount account) throws RemoteException;
    BankAccount searchAccount(String firstName, String lastName) throws RemoteException;
    BankAccount searchAccount(String accountNumber) throws RemoteException;
}
