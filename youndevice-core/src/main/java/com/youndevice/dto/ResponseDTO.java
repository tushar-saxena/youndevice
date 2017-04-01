package com.youndevice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ResponseDTO<T> {

    protected Boolean status = true;
    protected String message;
    protected T data;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ErrorDTO> errors;

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

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }
}
