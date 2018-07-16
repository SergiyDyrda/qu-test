package com.qualityunit.task;

import com.qualityunit.task.entity.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sergiy Dyrda on 16.07.2018
 */
    /*
        C 1.1 8.15.1 P 15.10.2012 83
        C 1 10.1 P 01.12.2012 65
        C 1.1 5.5.1 P 01.11.2012 117
        D 1.1 8 P 01.01.2012-01.12.2012
        C 3 10.2 N 02.10.2012 100
        D 1 * P 8.10.2012-20.11.2012
        D 3 10 P 01.12.2012
    */

public class TestData {

    public static WaitTimeLine waitTimeLine_1 = new WaitTimeLine(
            new Service("1", "1"),
            new Question("8", "15", "1"),
            Record.ResponseType.of("P"),
            LocalDate.of(2012, 10, 15),
            83L
    );

    public static WaitTimeLine waitTimeLine_2 = new WaitTimeLine(
            new Service("1", null),
            new Question("10", "1", null),
            Record.ResponseType.of("P"),
            LocalDate.of(2012, 12, 01),
            65L
    );

    public static WaitTimeLine waitTimeLine_3 = new WaitTimeLine(
            new Service("1", "1"),
            new Question("5", "5", "1"),
            Record.ResponseType.of("P"),
            LocalDate.of(2012, 11, 01),
            117L
    );


    public static Query query_1 = new Query(
            new Service("1", "1"),
            new Question("8", null, null),
            Record.ResponseType.of("P"),
            LocalDate.of(2012, 1, 1),
            LocalDate.of(2012, 12, 01)

    );

    public static WaitTimeLine waitTimeLine_4 = new WaitTimeLine(
            new Service("3", null),
            new Question("10", "2", null),
            Record.ResponseType.of("N"),
            LocalDate.of(2012, 10, 02),
            100L
    );

    public static Query query_2 = new Query(
            new Service("1", null),
            new Question.AnyQuestion(),
            Record.ResponseType.of("P"),
            LocalDate.of(2012, 10, 8),
            LocalDate.of(2012, 11, 20)

    );

    public static Query query_3 = new Query(
            new Service("3", null),
            new Question("10", null, null),
            Record.ResponseType.of("P"),
            LocalDate.of(2012, 12, 01),
            LocalDate.now()

    );

    public static List<Record> allRecords = Arrays.asList(
            waitTimeLine_1,
            waitTimeLine_2,
            waitTimeLine_3,
            query_1,
            waitTimeLine_4,
            query_2,
            query_3
    );

}
