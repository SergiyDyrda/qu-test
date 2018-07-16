package com.qualityunit.task.analyzer;

import com.qualityunit.task.TestData;
import com.qualityunit.task.entity.*;
import org.junit.Before;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class QueryAnalyzerTest {

    private QueryAnalyzer queryAnalyzer;


    @Before
    public void setUp() {
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

    @org.junit.Test
    public void getAverageWaitingTime_1() {
        Double result = queryAnalyzer.getAverageWaitingTime(TestData.query_1,
                Arrays.asList(
                        TestData.waitTimeLine_1,
                        TestData.waitTimeLine_2,
                        TestData.waitTimeLine_3
                ));

        assertEquals(Double.valueOf(83d), result);
    }

    @org.junit.Test
    public void getAverageWaitingTime_2() {
        Double result = queryAnalyzer.getAverageWaitingTime(TestData.query_2,
                Arrays.asList(
                        TestData.waitTimeLine_1,
                        TestData.waitTimeLine_2,
                        TestData.waitTimeLine_3,
                        TestData.waitTimeLine_4
                ));

        assertEquals(Double.valueOf(100d), result);
    }

    @org.junit.Test
    public void getAverageWaitingTime_3() {
        Double result = queryAnalyzer.getAverageWaitingTime(TestData.query_3,
                Arrays.asList(
                        TestData.waitTimeLine_1,
                        TestData.waitTimeLine_2,
                        TestData.waitTimeLine_3,
                        TestData.waitTimeLine_4
                ));

        assertEquals(Double.valueOf(0), result);
    }
}