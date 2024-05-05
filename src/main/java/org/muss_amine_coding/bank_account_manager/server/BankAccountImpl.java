package org.muss_amine_coding.bank_account_manager.server;

import org.muss_amine_coding.bank_account_manager.remote.BankAccount;
import org.muss_amine_coding.bank_account_manager.remote.InsufficientFundsException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import static org.muss_amine_coding.bank_account_manager.server.BankAccountManagerImpl.BANK_ACCOUNTS;

public class BankAccountImpl extends UnicastRemoteObject implements BankAccount {
    private String holderFirstName;
    private String holderLastName;
    private String accountNumber;
    private Double balance;


    public BankAccountImpl(String holderFirstName, String holderLastName) throws RemoteException {
        super();
        setHolderFirstName(holderFirstName);
        setHolderLastName(holderLastName);
        setBalance(0.0);
        setAccountNumber(generateAccountNumber());
    }

    public String getHolderFirstName() {
        return holderFirstName;
    }

    public void setHolderFirstName(String holderFirstName) {
        this.holderFirstName = holderFirstName;
    }

    public String getHolderLastName() {
        return holderLastName;
    }

    public void setHolderLastName(String holderLastName) {
        this.holderLastName = holderLastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getFullName() throws RemoteException {
        return getHolderFirstName()+" "+getHolderLastName();
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) throws RemoteException {
        setBalance(this.balance + amount);
    }

    public boolean withdraw(double amount) throws InsufficientFundsException, RemoteException {
        if (this.balance >= amount) {
            setBalance(this.balance - amount);
            return true;
        } else {
            throw new InsufficientFundsException("Insufficient balance");
        }
    }

    public double checkBalance() throws RemoteException {
        return this.getBalance();
    }

    public void transfer(String accountNumber, double amount) throws InsufficientFundsException, RemoteException {
        BankAccountImpl recipientAccount = searchAccount(accountNumber);
        if (recipientAccount != null) {
            if (this.withdraw(amount)) {
                recipientAccount.deposit(amount);
                System.out.println("Transferred from "+recipientAccount.getHolderFirstName()+" to "+getHolderFirstName());
            } else {
                throw new InsufficientFundsException("Insufficient balance");
            }
        }
    }

    private BankAccountImpl searchAccount(String accountNumber) throws RemoteException {
        for (BankAccount account: BANK_ACCOUNTS) {
            if (Objects.equals(account.getAccountNumber(), accountNumber))
                return (BankAccountImpl) account;
        }
        return null;
    }

    private String generateAccountNumber() {
        List<String> name = List.of((holderFirstName + holderLastName).split(" "));
        String fullName = "";
        String allPossibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabdefghijklmnopqrstuvwxyz0123456789";
        for (String n: name) {
            fullName = fullName.concat(n);
        }
        StringBuilder numberRepresentationOfName = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < fullName.length(); i++) {
            char randomChar = fullName.charAt(random.nextInt(0, fullName.length()));
            if (random.nextInt() % 2 == 0) {
                numberRepresentationOfName.append((int) randomChar);
            } else {
                numberRepresentationOfName.append(randomChar);
            }
        }
        String accountNumber = numberRepresentationOfName.toString();
        if (accountNumber.length() > 16) {
            accountNumber = accountNumber.substring(0, 16);
        } else if (accountNumber.length() < 16) {
            int charsToAdd = 16 - accountNumber.length();
            for (int i = 0; i < charsToAdd; i++) {
                accountNumber = accountNumber.concat(
                        String.valueOf(
                                allPossibleChars.charAt(
                                        random.nextInt(
                                                0,
                                                allPossibleChars.length()
                                        )
                                )
                        )
                );
            }
        }

        return accountNumber;
    }

    public static void main(String[] args) throws RemoteException {
        String generated = new BankAccountImpl("Amine", "Nimour").generateAccountNumber();
        System.out.println(generated+"\n"+"size = "+generated.length());
    }
}
