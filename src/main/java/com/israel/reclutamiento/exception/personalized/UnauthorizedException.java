package com.israel.reclutamiento.exception.personalized;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg){ super(msg); }
}