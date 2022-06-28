package com.peaksoft.examrestapijwttoken.exception;

public class ThereIsSuchAName extends RuntimeException{
    public ThereIsSuchAName() {
    }

    public ThereIsSuchAName(String message) {
        super(message);
    }
}
