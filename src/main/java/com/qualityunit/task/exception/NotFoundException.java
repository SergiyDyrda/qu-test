package com.qualityunit.task.exception;

/**
 * Created by Sergiy Dyrda on 15.07.2018
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
