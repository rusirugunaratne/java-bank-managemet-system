package com.banksystem;

public class Validation {
    public static boolean usernameValidation(String username) {
        boolean ex = username.contains("!");
        boolean space = username.contains(" ");
        return !(ex || space);
    }

    public static boolean passwordValidation(String password) {
        boolean ex = password.contains("!");
        boolean space = password.contains(" ");
        return !(ex || space);
    }

}
