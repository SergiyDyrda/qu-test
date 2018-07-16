package com.qualityunit.task.entity;

import com.qualityunit.task.exception.NotFoundException;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */

public class Record {

    private Service service;
    private Question question;
    private ResponseType responseType;
    private LocalDate date;

    public Record() {
    }

    public Record(Service service, Question question, ResponseType responseType, LocalDate date) {
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.date = date;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;

        Record record = (Record) o;

        if (service != null ? !service.equals(record.service) : record.service != null) return false;
        if (question != null ? !question.equals(record.question) : record.question != null) return false;
        if (responseType != record.responseType) return false;

        return date != null ? date.equals(record.date) : record.date == null;
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
                    .orElseThrow(() ->
                    new NotFoundException("Not  found responseType with label: " + label));
        }
    }

}
