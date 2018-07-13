package com.qualityunit.task.analyzer;

import com.qualityunit.task.entity.*;
import org.junit.Before;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class QueryAnalyzerTest {

    private QueryAnalyzer queryAnalyzer;


    @Before
    public void setUp() throws Exception {
        queryAnalyzer = new QueryAnalyzer();
    }

    @org.junit.Test
    public void getSuitableRecords_1() {
        List<Long> result = queryAnalyzer.getSuitableRecords(TestData.query_1,
                Arrays.asList(
                        TestData.waitTimeLine_1,
                        TestData.waitTimeLine_2,
                        TestData.waitTimeLine_3
                )).stream().map(WaitTimeLine::getWaitingTime)
                .collect(Collectors.toList());

        assertEquals(Collections.singletonList(83L), result);
    }


    @org.junit.Test
    public void getSuitableRecords_2() {
        List<Long> result = queryAnalyzer.getSuitableRecords(TestData.query_2,
                Arrays.asList(
                        TestData.waitTimeLine_1,
                        TestData.waitTimeLine_2,
                        TestData.waitTimeLine_3,
                        TestData.waitTimeLine_4
                )).stream().map(WaitTimeLine::getWaitingTime)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(83L, 117L), result);
    }

    @org.junit.Test
    public void getSuitableRecords_3() {
        List<Long> result = queryAnalyzer.getSuitableRecords(TestData.query_3,
                Arrays.asList(
                        TestData.waitTimeLine_1,
                        TestData.waitTimeLine_2,
                        TestData.waitTimeLine_3,
                        TestData.waitTimeLine_4
                )).stream().map(WaitTimeLine::getWaitingTime)
                .collect(Collectors.toList());

        assertEquals(Collections.emptyList(), result);
    }

    /*
        C 1.1 8.15.1 P 15.10.2012 83
        C 1 10.1 P 01.12.2012 65
        C 1.1 5.5.1 P 01.11.2012 117
        D 1.1 8 P 01.01.2012-01.12.2012
        C 3 10.2 N 02.10.2012 100
        D 1 * P 8.10.2012-20.11.2012
        D 3 10 P 01.12.2012
    */

    private static class TestData {

        static WaitTimeLine waitTimeLine_1 = new WaitTimeLine(
                new Service("1", "1"),
                new Question("8", "15", "1"),
                Record.ResponseType.of("P"),
                LocalDate.of(2012, 10, 15),
                83L
        );

        static WaitTimeLine waitTimeLine_2 = new WaitTimeLine(
                new Service("1", null),
                new Question("10", "1", null),
                Record.ResponseType.of("P"),
                LocalDate.of(2012, 12, 01),
                65L
        );

        static WaitTimeLine waitTimeLine_3 = new WaitTimeLine(
                new Service("1", "1"),
                new Question("5", "5", "1"),
                Record.ResponseType.of("P"),
                LocalDate.of(2012, 11, 01),
                117L
        );


        static Query query_1 = new Query(
                new Service("1", "1"),
                new Question("8", null, null),
                Record.ResponseType.of("P"),
                LocalDate.of(2012, 1, 1),
                LocalDate.of(2012, 12, 01)

        );

        static WaitTimeLine waitTimeLine_4 = new WaitTimeLine(
                new Service("3", null),
                new Question("10", "2", null),
                Record.ResponseType.of("N"),
                LocalDate.of(2012, 10, 02),
                100L
        );

        //         D 1 * P 8.10.2012-20.11.2012
//    D 3 10 P 01.12.2012
        static Query query_2 = new Query(
                new Service("1", null),
                new Question.AnyQuestion(),
                Record.ResponseType.of("P"),
                LocalDate.of(2012, 10, 8),
                LocalDate.of(2012, 11, 20)

        );

        static Query query_3 = new Query(
                new Service("3", null),
                new Question("10", null, null),
                Record.ResponseType.of("P"),
                LocalDate.of(2012, 12, 01),
                LocalDate.now()

        );

    }
}