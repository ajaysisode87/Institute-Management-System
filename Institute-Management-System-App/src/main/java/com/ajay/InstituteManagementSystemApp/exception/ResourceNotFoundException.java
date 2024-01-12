package com.ajay.InstituteManagementSystemApp.exception;

public class ResourceNotFoundException extends RuntimeException{

    Long fieldId;
    String fieldName;
    String field;
    String resourceName;

    public ResourceNotFoundException(String resourceName,String fieldName,Long fieldId){
        super(String.format ("%s  not found with %s : %s ",resourceName,fieldName,fieldId));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldId=fieldId;
    }

    public ResourceNotFoundException(String resourceName,String fieldName,String field){
        super();
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.field=field;
    }

}
