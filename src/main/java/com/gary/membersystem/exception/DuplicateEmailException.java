package com.gary.membersystem.exception;

public class DuplicateEmailException
        extends RuntimeException {

    public DuplicateEmailException(
            String message) {

        super(message);
    }
}