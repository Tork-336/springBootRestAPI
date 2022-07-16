package com.prograCol.controller;

public class ResponseGeneralAPI<T> {

    private T data;
    private String message;
    private int total;
    private boolean success;

    public ResponseGeneralAPI(T data, String message, int total, boolean success) {
        this.data = data;
        this.message = message;
        this.total = total;
        this.success = success;
    }

    public ResponseGeneralAPI(T data, String menssage, boolean success) {
        this.data = data;
        this.message = menssage;
        this.success = success;
    }

    public ResponseGeneralAPI() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
