package com.applicationAPI.execption;

public class ValidateSession extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidateSession(String mensaje) {
        super(mensaje);
    }
}
