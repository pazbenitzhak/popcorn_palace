package com.att.tdp.popcorn_palace;

public class ShowtimeNotExistsForBookingException extends RuntimeException {
    public ShowtimeNotExistsForBookingException(String msg) {
        super(msg);
    }
}