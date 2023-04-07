package com.example.board.exceptions;

public class UserWithEmailAlreadyExist extends RuntimeException {
    public UserWithEmailAlreadyExist(String msg) {
        super(msg);
    }
}
