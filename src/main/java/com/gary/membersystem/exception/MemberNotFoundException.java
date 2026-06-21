package com.gary.membersystem.exception;

public class MemberNotFoundException
        extends RuntimeException {

    public MemberNotFoundException(
            String message) {

        super(message);
    }
}