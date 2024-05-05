package org.muss_amine_coding.bank_account_manager.server;

import org.muss_amine_coding.bank_account_manager.remote.BankAccountManager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BankServer {
    public static void main(String[] args) {
        try {
            BankAccountManager bankAccountManager = new BankAccountManagerImpl();

            Registry registry = LocateRegistry.createRegistry(1010);

            //registry.rebind("BankAccountManager", bankAccountManager);
            Naming.rebind("//:1010/BankAccountManager", bankAccountManager);

            System.out.println("Server is up... you can bank with us NOW!!");
        } catch(RemoteException remoteException) {
            remoteException.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
