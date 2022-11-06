package com.banksystem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CustomerController {
    public static void addCustomer(Customer customer) throws Exception {
        String customerString;
        Customer tempCustomer = new Customer();
        File file = new File("Customers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        boolean found = false;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            customerString = randomAccessFile.readLine();
            String[] lineSplit = customerString.split("!");
            tempCustomer.setUsername(lineSplit[0]);
            tempCustomer.setPassword(lineSplit[1]);
            tempCustomer.setBalance(Double.parseDouble(lineSplit[2]));
            if (customer.getUsername().equals(tempCustomer.getUsername())) {
                found = true;
                break;
            }
        }
        if (!found) {
            customerString = customer.getUsername() + "!" + customer.getPassword() + "!" + customer.getBalance();
            randomAccessFile.writeBytes(customerString);
            randomAccessFile.writeBytes(System.lineSeparator());
            System.out.println("+--------------------------+");
            System.out.println("Customer Successfully Added");
            System.out.println("+--------------------------+");
            randomAccessFile.close();
        } else {
            randomAccessFile.close();
            System.out.println("+--------------------------+");
            System.out.println(customer.getUsername() + " User already exists");
            System.out.println("+--------------------------+");
        }

    }

    public static void removeCustomer(Customer customer) throws Exception {
        String customerString;
        Customer tempCustomer = new Customer();
        File file = new File("Customers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        boolean found = false;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            customerString = randomAccessFile.readLine();
            String[] lineSplit = customerString.split("!");
            tempCustomer.setUsername(lineSplit[0]);
            tempCustomer.setPassword(lineSplit[1]);
            tempCustomer.setBalance(Double.parseDouble(lineSplit[2]));
            if (customer.getUsername().equals(tempCustomer.getUsername())) {
                found = true;
                break;
            }
        }
        if (found) {
            File tempFile = new File("temp.txt");
            RandomAccessFile temprnd = new RandomAccessFile(tempFile, "rw");
            randomAccessFile.seek(0);
            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                customerString = randomAccessFile.readLine();
                String[] lineSplit = customerString.split("!");
                tempCustomer.setUsername(lineSplit[0]);
                tempCustomer.setPassword(lineSplit[1]);
                if (customer.getUsername().equals(tempCustomer.getUsername()) && customer.getPassword().equals(tempCustomer.getPassword())) {
                    continue;
                }
                temprnd.writeBytes(customerString);
                temprnd.writeBytes(System.lineSeparator());
            }
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
            System.out.println("+--------------------------+");
            System.out.println("Customer Deleted");
            System.out.println("+--------------------------+");
        } else {
            randomAccessFile.close();
            System.out.println("+--------------------------+");
            System.out.println(customer.getUsername() + " does not exist");
            System.out.println("+--------------------------+");
        }

    }

    public static Customer customerLogin(Customer customer) throws Exception {
        ///return null;
        String customerString;
        Customer tempCustomer = new Customer();
        File file = new File("Customers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        boolean found = false;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            customerString = randomAccessFile.readLine();
            String[] lineSplit = customerString.split("!");
            tempCustomer.setUsername(lineSplit[0]);
            tempCustomer.setPassword(lineSplit[1]);
            tempCustomer.setBalance(Double.parseDouble(lineSplit[2]));
            if (customer.getUsername().equals(tempCustomer.getUsername()) && customer.getPassword().equals(tempCustomer.getPassword())) {
                found = true;
                break;
            }
        }
        randomAccessFile.close();
        if (!found) {

            return null;
        } else {
            return tempCustomer;
        }
    }

    public static Customer findCustomer(Customer customer) throws Exception {
        ///return null;
        String customerString;
        Customer tempCustomer = new Customer();
        File file = new File("Customers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        boolean found = false;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            customerString = randomAccessFile.readLine();
            String[] lineSplit = customerString.split("!");
            tempCustomer.setUsername(lineSplit[0]);
            tempCustomer.setPassword(lineSplit[1]);
            tempCustomer.setBalance(Double.parseDouble(lineSplit[2]));
            if (customer.getUsername().equals(tempCustomer.getUsername())) {
                found = true;
                break;
            }
        }
        randomAccessFile.close();
        if (!found) {

            return null;
        } else {
            return tempCustomer;
        }
    }

    public static void viewCustomers() throws Exception {
        String customerString;
        Customer tempCustomer = new Customer();
        File file = new File("Customers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Username            Balance");
        System.out.println("---------------------------------------------------");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        boolean found = false;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            customerString = randomAccessFile.readLine();
            String[] lineSplit = customerString.split("!");
            tempCustomer.setUsername(lineSplit[0]);
            tempCustomer.setPassword(lineSplit[1]);
            tempCustomer.setBalance(Double.parseDouble(lineSplit[2]));
            System.out.println(tempCustomer.getUsername() + "            " + tempCustomer.getBalance());
            System.out.println("---------------------------------------------------");


        }
    }

    public static void updateCustomerBalance(Customer customer, double finalBalance) throws Exception {
        String customerString;
        Customer tempCustomer = new Customer();
        File file = new File("Customers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        boolean found = false;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            customerString = randomAccessFile.readLine();
            String[] lineSplit = customerString.split("!");
            tempCustomer.setUsername(lineSplit[0]);
            tempCustomer.setPassword(lineSplit[1]);
            tempCustomer.setBalance(Double.parseDouble(lineSplit[2]));
            if (customer.getUsername().equals(tempCustomer.getUsername())) {
                found = true;
                break;
            }
        }
        if (found) {
            File tempFile = new File("temp.txt");
            RandomAccessFile temprnd = new RandomAccessFile(tempFile, "rw");
            randomAccessFile.seek(0);
            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                customerString = randomAccessFile.readLine();
                String[] lineSplit = customerString.split("!");
                tempCustomer.setUsername(lineSplit[0]);
                tempCustomer.setPassword(lineSplit[1]);
                if (customer.getUsername().equals(tempCustomer.getUsername()) && customer.getPassword().equals(tempCustomer.getPassword())) {
                    customerString = customer.getUsername() + "!" + customer.getPassword() + "!" + finalBalance;
                }
                temprnd.writeBytes(customerString);
                temprnd.writeBytes(System.lineSeparator());
            }
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


        } else {
            randomAccessFile.close();
            System.out.println("+--------------------------+");
            System.out.println(customer.getUsername() + " does not exist");
            System.out.println("+--------------------------+");
        }

    }

    public static Customer transferCustomerLogin(Customer customer) throws Exception {
        ///return null;
        String customerString;
        Customer tempCustomer = new Customer();
        File file = new File("Customers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        boolean found = false;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            customerString = randomAccessFile.readLine();
            String[] lineSplit = customerString.split("!");
            tempCustomer.setUsername(lineSplit[0]);
            tempCustomer.setPassword(lineSplit[1]);
            tempCustomer.setBalance(Double.parseDouble(lineSplit[2]));
            if (customer.getUsername().equals(tempCustomer.getUsername())) {
                found = true;
                break;
            }
        }
        randomAccessFile.close();
        if (!found) {

            return null;
        } else {
            return tempCustomer;
        }
    }
}
