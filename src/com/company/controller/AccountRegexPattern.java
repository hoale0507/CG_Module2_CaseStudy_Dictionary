package com.company.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountRegexPattern {
    private static final String ID_REGEX = "^[a-z0-9]{6,12}$";
    private static final String PASSWORD_REGEX = "^.{6,12}$";

    public AccountRegexPattern() {
    }

    public boolean validateID(String id){
        Pattern pattern = Pattern.compile(ID_REGEX);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public boolean validatePassword(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
