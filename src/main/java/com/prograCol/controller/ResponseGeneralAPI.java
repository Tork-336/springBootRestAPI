package com.prograCol.controller;

public class ResponseGeneralAPI<T> {

    private T data;
    private String message;
    private int total;

    public ResponseGeneralAPI(T data, String message, int total) {
        this.data = data;
        this.message = message;
        this.total = total;
    }

    public ResponseGeneralAPI(T data, String menssage) {
        this.data = data;
        this.message = menssage;
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
}
