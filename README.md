# Java RMI Bank Account Manager

This project demonstrates a simple bank account management system implemented in Java using Remote Method Invocation (RMI).

## Overview

The bank account management system consists of two main components:

1. **Server**: The server hosts the bank account manager, which allows clients to create new bank accounts, deposit money, withdraw money, transfer funds between accounts, and close accounts.

2. **Client**: The client interacts with the server by invoking methods on the bank account manager remotely using RMI. Clients can perform various banking operations such as creating accounts, depositing money, withdrawing money, transferring funds, and closing accounts.

## Project Structure

The project is organized into the following components:

- `BankAccount.java`: Interface defining the operations that can be performed on a bank account.
- `BankAccountManager.java`: Interface defining the operations that can be performed by the bank account manager.
- `BankAccountImpl.java`: Implementation of the `BankAccount` interface representing a concrete bank account.
- `BankAccountManagerImpl.java`: Implementation of the `BankAccountManager` interface representing a concrete bank account manager.
- `BankServer.java`: Main class for the server that starts the RMI registry and binds the bank account manager object.
- `BankClient.java`: Main class for the client that connects to the server and interacts with the bank account manager.
- `InsufficientFundsException.java`: Custom exception class thrown when a withdrawal cannot be completed due to insufficient funds.

## Getting Started

1. Clone the repository:

    ```bash
    git clone https://github.com/mussrabah/BankAccountManager.git
    ```

2. Compile the Java files:

    ```bash
    javac *.java
    ```

3. Start the server:

    ```bash
    java BankServer
    ```

4. Run the client:

    ```bash
    java BankClient
    ```

## Dependencies

- Java Development Kit (JDK) 21

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
