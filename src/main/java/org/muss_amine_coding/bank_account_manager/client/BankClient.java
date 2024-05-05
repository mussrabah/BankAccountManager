package org.muss_amine_coding.bank_account_manager.client;

import org.muss_amine_coding.bank_account_manager.remote.BankAccount;
import org.muss_amine_coding.bank_account_manager.remote.BankAccountManager;
import org.muss_amine_coding.bank_account_manager.remote.InsufficientFundsException;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;

public class BankClient {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";


    public static void main(String[] args) {
        try {
            //Registry registry = LocateRegistry.getRegistry("localhost", 1010);
            //BankAccountManager bankAccountManager = (BankAccountManager) registry.lookup("BankAccountManager");
            BankAccountManager bankAccountManager = (BankAccountManager) Naming
                    .lookup("rmi://localhost:1010/BankAccountManager");

            accountManipulation(bankAccountManager);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void accountManipulation(BankAccountManager bankAccountManager) throws RemoteException, InsufficientFundsException, SQLException {
        BankAccount activeAccount;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to our bank!!");
            System.out.println("Please choose an operation");
            System.out.println("""
                    1. Create account.
                    2. Open an existing account.
                    3. exit.
                    """);
            int operation = scanner.nextInt();
            scanner.nextLine();
            switch (operation) {
                case 1 -> {
                    System.out.println("Enter first name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter last name:");
                    String lastName = scanner.nextLine();
                    activeAccount = bankAccountManager.createAccount(firstName, lastName);
                    accountOperations(activeAccount, bankAccountManager);
                }
                case 2 -> {
                    System.out.println("Access it with:");
                    System.out.println("""
                            1. First name and last name.
                            2. Account number.
                            """);
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Enter first name:");
                            String firstName = scanner.nextLine();
                            System.out.println("Enter last name:");
                            String lastName = scanner.nextLine();
                            activeAccount = bankAccountManager.searchAccount(firstName, lastName);
                            if (activeAccount != null) {
                                accountOperations(activeAccount, bankAccountManager);
                            } else {
                                System.out.println(ANSI_RED+"No account exist with the infos you provided"+ANSI_RESET);
                            }
                        }
                        case 2 -> {
                            System.out.println("Enter account number:");
                            String accountNumber = scanner.nextLine();
                            activeAccount = bankAccountManager.searchAccount(accountNumber);
                            if (activeAccount != null) {
                                accountOperations(activeAccount, bankAccountManager);
                            } else {
                                System.out.println(ANSI_RED+"No account exist with the infos you provided"+ANSI_RESET);
                            }
                        }
                        default -> {
                            System.out.println(ANSI_RED+"False choice"+ANSI_RESET);
                        }
                    }
                }
                case 3 -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println(ANSI_RED+"Enter a correct choice"+ANSI_RESET);
                }
            }
        }
    }

    private static void accountOperations(BankAccount account, BankAccountManager bankAccountManager) throws RemoteException, InsufficientFundsException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "\n---------------Account Details ---------------");
        System.out.println("Account's holder full name: "+account.getFullName());
        System.out.println("Account's number: "+account.getAccountNumber());
        System.out.println("Account's balance: "+account.checkBalance());
        System.out.println("----------------------------------------------\n"+ ANSI_RESET);
        while (true) {
            System.out.println("What would you do with your account:");
            System.out.println("""
                1. Deposit.
                2. Withdraw.
                3. Check balance.
                4. Transfer.
                5. Get account number.
                6. Close account.
                7. exit.
                """);
            int operation = scanner.nextInt();
            scanner.nextLine();
            switch (operation) {
                case 1 -> {
                    System.out.println("Enter amount to deposit:");
                    account.deposit(scanner.nextDouble());
                }
                case 2 -> {
                    System.out.println("Enter amount to withdraw:");
                    account.withdraw(scanner.nextDouble());
                }
                case 3 -> {
                    System.out.println(ANSI_YELLOW + "Your account's balance is: "+account.checkBalance() + ANSI_RESET);
                }
                case 4 -> {
                    System.out.println("Enter receiver's account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.println("Enter the amount you want to transfer:");
                    double amount = scanner.nextDouble();
                    account.transfer(accountNumber, amount);
                    System.out.println(ANSI_YELLOW+"Transferred successfully"+ANSI_RESET);
                }
                case 5 -> {
                    System.out.println(ANSI_YELLOW+"Your account's number is: "+account.getAccountNumber()+ANSI_RESET);
                }
                case 6 -> {
                    bankAccountManager.closeAccount(account);
                }
                case 7 -> {
                    return;
                }
                default -> {
                    System.out.println(ANSI_RED+"Enter correct choice"+ANSI_RESET);
                }
            }
        }
    }
}
