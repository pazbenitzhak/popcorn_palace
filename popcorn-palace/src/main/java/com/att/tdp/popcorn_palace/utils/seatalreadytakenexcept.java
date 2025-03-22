package com.att.tdp.popcorn_palace;

public class SeatAlreadyTakenException extends RuntimeException {
    public SeatAlreadyTakenException(String msg) {
        super(msg);
    }
}