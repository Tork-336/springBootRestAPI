package com.applicationAPI.execption;

public class ModelNotFoundExecption extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ModelNotFoundExecption(String msg) {
        super(msg);
    }
}
