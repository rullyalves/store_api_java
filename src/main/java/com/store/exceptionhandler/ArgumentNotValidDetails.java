package com.store.exceptionhandler;

public class ArgumentNotValidDetails {

    private String fieldName;
    private String rejectedValue;
    private String message;

    public ArgumentNotValidDetails(String fieldName, String rejectedValue, String message) {
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRejectedValue() {
        return this.rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}