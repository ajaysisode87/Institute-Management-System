package com.ajay.InstituteManagementSystemApp.exception;

public class InstituteNotFoundException extends RuntimeException{
    public String errorMessage;
    public InstituteNotFoundException(String message){
        super(message);
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage=errorMessage;
    }
}
