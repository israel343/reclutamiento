package com.israel.reclutamiento.exception.personalized;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg){ super(msg); }
}