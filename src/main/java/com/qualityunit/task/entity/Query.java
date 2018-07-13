package com.qualityunit.task.entity;

import java.time.LocalDate;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
public class Query extends Record {
    private LocalDate dateTo;

    public Query(Service service, Question question, ResponseType responseType, LocalDate date, LocalDate dateTo) {
        super(service, question, responseType, date);
        this.dateTo = dateTo;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
