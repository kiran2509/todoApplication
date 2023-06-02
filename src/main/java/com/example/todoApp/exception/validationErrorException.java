package com.example.todoApp.exception;

public class validationErrorException extends  Exception{

    public validationErrorException(String message) {
        super(message);
    }

    public validationErrorException(Throwable cause) {
        super(cause);
    }
}
