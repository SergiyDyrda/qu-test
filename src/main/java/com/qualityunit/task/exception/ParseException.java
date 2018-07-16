package com.qualityunit.task.exception;

/**
 * Created by Sergiy Dyrda on 15.07.2018
 */
public class ParseException extends RuntimeException {
    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }
}
