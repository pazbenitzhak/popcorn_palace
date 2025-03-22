package com.att.tdp.popcorn_palace.utils;

public class SeatAlreadyTakenException extends RuntimeException {
    public SeatAlreadyTakenException(String msg) {
        super(msg);
    }
}