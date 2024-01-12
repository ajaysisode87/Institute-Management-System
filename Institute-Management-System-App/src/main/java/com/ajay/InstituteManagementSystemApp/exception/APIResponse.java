package com.ajay.InstituteManagementSystemApp.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponse<T> {
    private int status;
    private String message;
    private T data;
    private boolean response;

    public APIResponse(){}

    public APIResponse(int status, String message, T data, boolean response) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.response = response;
    }

    public APIResponse(String s) {
    }
}
