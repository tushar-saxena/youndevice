package com.youndevice.dto;


public class ResponseDTO<T> {

    protected  Boolean status = true;
    protected String message;
    protected T data;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    void setFailureResponse(String message) {
        this.message = message;
        status = false;
    }

}
