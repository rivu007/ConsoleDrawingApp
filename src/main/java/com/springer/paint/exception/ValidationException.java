package com.springer.paint.exception;

/**
 * The exception indicates that the validation on components failed
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.2
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }
}
