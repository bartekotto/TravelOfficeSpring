package com.example.traveloffice;

public class NoSuchCustomerException extends Exception {
    public NoSuchCustomerException() {
    }

    public NoSuchCustomerException(String message) {
        super(message);
    }
}
