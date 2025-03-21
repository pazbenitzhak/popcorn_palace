package com.att.tdp.popcorn_palace;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String msg) {
        super(msg);
    }
}