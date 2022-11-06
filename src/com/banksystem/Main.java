package com.banksystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("+**************************+");
            System.out.println("|        Bank System       |");
            System.out.println("+**************************+");
            System.out.println("Select an Option ");
            int option;
            System.out.println("    [1]Create Bank Account");
            System.out.println("    [2]Log into bank Account(Deposit, Withdraw, Transfer and Request Balance)");
            System.out.println("    [3]Close Bank Account");
            System.out.println("    [4]View all Customers");
            System.out.println("    [5]View all Transactions");
            System.out.println("    [6]Exit");
            System.out.print("    Option Number: ");
            option = scanner.nextInt();
            if (option == 6) {
                break;
            }

            switch (option) {
                case 1:
                    createBankAccount();
                    break;
                case 2:
                    customerLoginMenu();
                    break;
                case 3:
                    closeBankAccount();
                    break;
                case 4:
                    viewAllCustomers();
                    break;
                case 5:
                    viewAllTransactions();
                    break;
            }

        }
        System.out.println("Thank you !");
    }

    public static void viewAllTransactions() {
        try {
            AdminController.viewTransactions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewAllCustomers() {
        try {
            AdminController.viewCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeBankAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+--------------------------+");
        System.out.println("|      Close Account       |");
        System.out.println("+--------------------------+");
        Customer customer = new Customer();
        System.out.print("    Username : ");
        customer.setUsername(scanner.nextLine());
        System.out.print("    Password : ");
        customer.setPassword(scanner.nextLine());
        try {

            CustomerController.removeCustomer(customer);
            CustomerController.viewCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void customerLoginMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+--------------------------+");
        System.out.println("|    Customer Login Menu   |");
        System.out.println("+--------------------------+");
        Customer customer = new Customer();
        System.out.print("    Username : ");
        customer.setUsername(scanner.nextLine());
        System.out.print("    Password : ");
        customer.setPassword(scanner.nextLine());
        Customer updatedCustomer = null;
        try {
            updatedCustomer = CustomerController.customerLogin(customer);
            if (updatedCustomer == null) {
                System.out.println("No such Customer");
            } else {
                int option;
                System.out.println("Welcome ! " + updatedCustomer.getUsername());

                System.out.println("Balance  : " + updatedCustomer.getBalance());
                System.out.println("--------------------------");

                System.out.println("--------------------------");
                System.out.println("Options : ");
                System.out.println("    [1]Deposit Money");
                System.out.println("    [2]Withdraw Money");
                System.out.println("    [3]Transfer Money");
                System.out.println("    [4]Request Balance");
                System.out.println("    [5]Exit");
                System.out.print("    Option  : ");
                option = scanner.nextInt();
                if (option != 5) {
                    if(option==4){
                        System.out.println("--------------------------");
                        System.out.println("Balance : "+ updatedCustomer.getBalance());
                        System.out.println("--------------------------");
                    }else{
                        if(option==3){
                            Customer transferCustomer = new Customer();
                            System.out.print("    Username of the account that the money will be transferred : ");

                            String transferUsername = scanner.next();
                            transferCustomer.setUsername(transferUsername);
                            if(CustomerController.findCustomer(transferCustomer)!=null){
                                transferCustomer = CustomerController.transferCustomerLogin(transferCustomer);
                                double transactionAmount = 0;
                                System.out.print("    Transaction Amount: ");
                                transactionAmount = scanner.nextDouble();
                                Transaction transaction = Transaction.makeTransactionObj(updatedCustomer, option, transactionAmount);
                                TransactionController.addTransaction(transaction);
                                Transaction transferTo = Transaction.makeTransactionObj(transferCustomer,1,transactionAmount);
                                TransactionController.addTransaction(transferTo);
                            }else{
                                System.out.println("--------------------------");
                                System.out.println("Money Transfer Failed! No such customer");
                                System.out.println("--------------------------");
                            }

                        }else{
                            double transactionAmount = 0;
                            System.out.print("    Transaction Amount: ");
                            transactionAmount = scanner.nextDouble();
                            Transaction transaction = Transaction.makeTransactionObj(updatedCustomer, option, transactionAmount);
                            TransactionController.addTransaction(transaction);
                        }

                    }

                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void createBankAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+--------------------------+");
        System.out.println("|    Customer SignUp Menu  |");
        System.out.println("+--------------------------+");
        Customer customer = new Customer();
        System.out.print("    Username : ");
        customer.setUsername(scanner.nextLine());
        System.out.print("    Password : ");
        customer.setPassword(scanner.nextLine());
        customer.setBalance(0);
        if (Validation.usernameValidation(customer.getUsername()) && Validation.passwordValidation(customer.getPassword())) {

            CustomerController customerController = new CustomerController();
            try {
                CustomerController.addCustomer(customer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Password or Username is not in valid format -> No spaces and no ! marks");
            System.out.println("-----------------------------------------------------------------------");
        }


    }


}

