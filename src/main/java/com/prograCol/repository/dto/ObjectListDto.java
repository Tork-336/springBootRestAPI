package com.prograCol.repository.dto;

public class ObjectListDto<T> {

    private T data;
    private long total;

    public ObjectListDto() {
    }

    public ObjectListDto(T data, long total) {
        this.data = data;
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
