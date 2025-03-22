package com.att.tdp.popcorn_palace.utils;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String msg) {
        super(msg);
    }
}