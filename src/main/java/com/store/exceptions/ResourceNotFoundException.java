package com.store.exceptions;

import java.io.Serializable;

public class ResourceNotFoundException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = -1649992452464587054L;

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message,cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }




}