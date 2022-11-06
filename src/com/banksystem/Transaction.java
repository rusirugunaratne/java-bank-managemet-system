package com.banksystem;

public class Transaction {
    private Customer customer;
    private double transactionAmount;
    private int transactionType; //1-> deposit  2-> withdraw 3->Transfer

    public Transaction() {
    }

    public Transaction(Customer customer, double transactionAmount, int transactionType) {
        this.customer = customer;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
    }

    public static Transaction makeTransactionObj(Customer customer, int type, double value) {
        return new Transaction(customer, value, type);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }
}
