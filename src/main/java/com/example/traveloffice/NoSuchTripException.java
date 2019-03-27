package com.example.traveloffice;

public class NoSuchTripException extends Exception {
    public NoSuchTripException() {
    }

    public NoSuchTripException(String message) {
        super(message);
    }
}
