package com.qualityunit.task.entity;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */

public abstract class Record {

    private Service service;
    private Question question;
    private ResponseType responseType;
    private LocalDate date;


    public Record(Service service, Question question, ResponseType responseType, LocalDate date) {
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.date = date;
    }

    public Service getService() {
        return service;
    }

    public Question getQuestion() {
        return question;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public LocalDate getDate() {
        return date;
    }

    public enum ResponseType {
        FIRST_ANSWER("P"),
        NEXT_ANSWER("N");

        private String label;

        ResponseType(String label) {
            this.label = label;
        }

        public static ResponseType of(String label) {
            return Stream.of(values())
                    .filter(v -> v.label.equals(label))
                    .findFirst()
                    .orElseGet(null);
        }
    }

}
