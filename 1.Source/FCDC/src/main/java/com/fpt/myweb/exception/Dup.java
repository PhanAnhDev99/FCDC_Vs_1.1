package com.fpt.myweb.exception;

public class Dup extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public Dup(String msg) {
        super(msg);
    }
}
