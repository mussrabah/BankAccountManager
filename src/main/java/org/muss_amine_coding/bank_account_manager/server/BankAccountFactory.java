package org.muss_amine_coding.bank_account_manager.server;

import org.muss_amine_coding.bank_account_manager.remote.BankAccount;

import java.rmi.RemoteException;

public interface BankAccountFactory {
    BankAccount createNewBankAccount(String firstName, String LastName) throws RemoteException;
}
