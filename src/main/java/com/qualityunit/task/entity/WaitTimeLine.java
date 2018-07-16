package com.qualityunit.task.entity;

import java.time.LocalDate;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
public class WaitTimeLine extends Record {
    private Long waitingTime;

    public WaitTimeLine() {
    }

    public WaitTimeLine(Service service, Question question, ResponseType responseType, LocalDate date, Long waitingTime) {
        super(service, question, responseType, date);
        this.waitingTime = waitingTime;
    }

    public WaitTimeLine(Record record) {
        this(record.getService(), record.getQuestion(), record.getResponseType(), record.getDate(), null);
    }

    public Long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Long waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaitTimeLine)) return false;
        if (!super.equals(o)) return false;

        WaitTimeLine line = (WaitTimeLine) o;

        return waitingTime != null ? waitingTime.equals(line.waitingTime) : line.waitingTime == null;
    }

}
