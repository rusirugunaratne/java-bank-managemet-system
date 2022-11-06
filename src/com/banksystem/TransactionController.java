package com.banksystem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TransactionController {
    public static boolean addTransaction(Transaction transaction) throws Exception {
        String transActionsType;
        if (transaction.getTransactionType() == 1) {
            transActionsType = "Deposit";
        } else if(transaction.getTransactionType()==2){
            transActionsType = "Withdraw";
        }else{
            transActionsType = "Transfer";
        }


        double finalBalance;
        if (transaction.getTransactionType() == 2 || transaction.getTransactionType()==3) {
            finalBalance = transaction.getCustomer().getBalance() - transaction.getTransactionAmount();
        } else {
            finalBalance = transaction.getCustomer().getBalance() + transaction.getTransactionAmount();
        }
        if (finalBalance < 0) {
            System.out.println("--------------------------");
            System.out.println("Balance is not Enough!");
            System.out.println("--------------------------");
            return false;
        } else {
            CustomerController.updateCustomerBalance(transaction.getCustomer(), finalBalance);
            String transactionString;
//


            File file = new File("Transactions.txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            {
                File tempFile = new File("temp.txt");
                RandomAccessFile temprnd = new RandomAccessFile(tempFile, "rw");
                randomAccessFile.seek(0);
                while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                    transactionString = randomAccessFile.readLine();

                    temprnd.writeBytes(transactionString);
                    temprnd.writeBytes(System.lineSeparator());
                }
                transactionString = transaction.getCustomer().getUsername() + "!" + transActionsType + "!" + transaction.getTransactionAmount() + "!" + finalBalance;
                temprnd.writeBytes(transactionString);
                randomAccessFile.seek(0);
                temprnd.seek(0);
                while (temprnd.getFilePointer() < temprnd.length()) {
                    randomAccessFile.writeBytes(temprnd.readLine());
                    randomAccessFile.writeBytes(System.lineSeparator());
                }
                randomAccessFile.setLength(temprnd.length());
                temprnd.close();
                randomAccessFile.close();
                tempFile.delete();
                if(transActionsType=="Deposit"){
                    System.out.println("Deposit added Successfully!");
                }else if (transActionsType=="Withdraw"){
                    System.out.println("Withdraw Successfully!");
                }else{
                    System.out.println("Transfer added Successfully!");
                }

                return true;

            }


        }
    }

    public static void viewTransactions() throws Exception {
        String transactionString;
        Transaction tempTransaction = new Transaction();
        File file = new File("Transactions.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Username            TransactionType            TransactionAmount            FinalBalance");
        System.out.println("-------------------------------------------------------------------------------------------");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        boolean found = false;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            transactionString = randomAccessFile.readLine();
            String[] lineSplit = transactionString.split("!");
            String uname = lineSplit[0];
            String ttype = lineSplit[1];
            String tamount = lineSplit[2];
            String fbal = lineSplit[3];
            System.out.println(uname + "               " + ttype + "                     " + tamount + "                        " + fbal);
            System.out.println("-------------------------------------------------------------------------------------------");

        }
    }
}
