package com.banksystem;

public class AdminController {
    public static void viewCustomers() throws Exception {
        CustomerController.viewCustomers();
    }

    public static void viewTransactions() throws Exception {
        TransactionController.viewTransactions();
    }
}
