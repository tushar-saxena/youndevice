package com.youndevice.rest.dto;

import com.youndevice.dto.ResponseDTO;

import javax.servlet.http.HttpServletResponse;


public class ApiResponseDTO<T> extends ResponseDTO<T> {
    int statusCode = HttpServletResponse.SC_OK;

    static ApiResponseDTO getErrorDto(String message) {
        return new ApiResponseDTO(message, false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public static ApiResponseDTO getSuccessDto(String message, Object data) {
        return new ApiResponseDTO(message, true, data);
    }


    void setInternalServerErrorResponse(Exception e, Integer code) {
        message = e.getMessage();
        statusCode = code;
        status = false;
    }

    void populateApiErrorResponseDTO(Exception e, String msg, Integer code) {
        message = (message != null) ? msg : e.getMessage();
        ;
        statusCode = code;
        status = false;
    }

    ApiResponseDTO(String message, Boolean status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    ApiResponseDTO(String message, boolean status, int statusCode) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
    }

    public ApiResponseDTO() {

    }
}
