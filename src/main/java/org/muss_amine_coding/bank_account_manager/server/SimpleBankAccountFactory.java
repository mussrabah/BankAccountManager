package org.muss_amine_coding.bank_account_manager.server;

import org.muss_amine_coding.bank_account_manager.remote.BankAccount;

import java.rmi.RemoteException;

public class SimpleBankAccountFactory implements BankAccountFactory{
    @Override
    public BankAccount createNewBankAccount(String firstName, String lastName) throws RemoteException {
        return new BankAccountImpl(firstName, lastName);
    }
}
