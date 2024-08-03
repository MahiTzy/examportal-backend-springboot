package com.examserver.helper;

public class UserFoundException extends Exception {

    public UserFoundException() {
        super("User with this username is already exists!");
    }

    public UserFoundException(String msg) {
        super(msg);
    }

}
