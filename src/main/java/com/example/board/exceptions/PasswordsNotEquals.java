package com.example.board.exceptions;

public class PasswordsNotEquals extends RuntimeException {
    public PasswordsNotEquals(String message) {
        super(message);
    }
}
