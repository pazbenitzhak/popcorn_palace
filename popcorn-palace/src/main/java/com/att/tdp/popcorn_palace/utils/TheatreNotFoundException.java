package com.att.tdp.popcorn_palace.utils;

public class TheatreNotFoundException extends RuntimeException {
    public TheatreNotFoundException(String msg) {
        super(msg);
    }
}