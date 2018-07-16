package com.qualityunit.task.entity;

import java.time.LocalDate;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
public class Query extends Record {
    private LocalDate dateTo;

    public Query() {
    }

    public Query(Service service, Question question, ResponseType responseType, LocalDate date, LocalDate dateTo) {
        super(service, question, responseType, date);
        this.dateTo = dateTo;
    }

    public Query(Record record) {
        this(record.getService(), record.getQuestion(), record.getResponseType(), record.getDate(), null);
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Query)) return false;
        if (!super.equals(o)) return false;

        Query query = (Query) o;

        return dateTo != null ? dateTo.equals(query.dateTo) : query.dateTo == null;
    }

}
