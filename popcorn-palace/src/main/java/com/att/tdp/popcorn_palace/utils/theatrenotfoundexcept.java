package com.att.tdp.popcorn_palace;

public class TheatreNotFoundException extends RuntimeException {
    public TheatreNotFoundException(String msg) {
        super(msg);
    }
}