package com.qualityunit.task.entity;

import java.time.LocalDate;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
public class WaitTimeLine extends Record {
    private Long waitingTime;

    public WaitTimeLine(Service service, Question question, ResponseType responseType, LocalDate date, Long waitingTime) {
        super(service, question, responseType, date);
        this.waitingTime = waitingTime;
    }

    public Long getWaitingTime() {
        return waitingTime;
    }
}
