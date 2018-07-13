package com.qualityunit.task.analyzer;

import com.qualityunit.task.entity.Record;

import java.util.List;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
public abstract class RecordsAnalyzer<QUERY extends Record, IN_RECORD extends Record> {

    private RecordMatcher<QUERY, IN_RECORD> recordMatcher;

    public RecordsAnalyzer(RecordMatcher<QUERY, IN_RECORD> recordMatcher) {
        this.recordMatcher = recordMatcher;
    }

    public abstract List<IN_RECORD> getSuitableRecords(QUERY query, List<IN_RECORD> records,
                                                       RecordMatcher<QUERY, IN_RECORD> matcher);


    public abstract List<IN_RECORD> getSuitableRecords(QUERY query, List<IN_RECORD> records);

}