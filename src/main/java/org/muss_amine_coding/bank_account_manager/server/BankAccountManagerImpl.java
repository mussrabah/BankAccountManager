package org.muss_amine_coding.bank_account_manager.server;

import org.muss_amine_coding.bank_account_manager.remote.BankAccount;
import org.muss_amine_coding.bank_account_manager.remote.BankAccountManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class BankAccountManagerImpl extends UnicastRemoteObject implements BankAccountManager {
    static final ArrayList<BankAccount> BANK_ACCOUNTS = new ArrayList<>();

    protected BankAccountManagerImpl() throws RemoteException {
    }

    @Override
    public BankAccount createAccount(String firstName, String LastName) throws RemoteException {
        BankAccount account = new SimpleBankAccountFactory().createNewBankAccount(firstName, LastName);
        BANK_ACCOUNTS.add(account);
        return account;
    }
    @Override
    public void closeAccount(BankAccount account) throws RemoteException {
        BANK_ACCOUNTS.remove(account);
    }

    @Override
    public BankAccount searchAccount(String firstName, String lastName) throws RemoteException {
        for (BankAccount account: BANK_ACCOUNTS) {
            if (Objects.equals(account.getFullName(), firstName + " " + lastName)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public BankAccount searchAccount(String accountNumber) throws RemoteException {
        for (BankAccount account: BANK_ACCOUNTS) {
            if (Objects.equals(account.getAccountNumber(), accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
