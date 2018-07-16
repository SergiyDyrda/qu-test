package com.qualityunit.task.exception;

/**
 * Created by Sergiy Dyrda on 15.07.2018
 */
public class NotReadableFileException extends RuntimeException {

    public NotReadableFileException(String message) {
        super(message);
    }

    public NotReadableFileException(Exception e) {
        super(e);
    }

    public NotReadableFileException(String message, Exception e) {
        super(message, e);
    }
}
