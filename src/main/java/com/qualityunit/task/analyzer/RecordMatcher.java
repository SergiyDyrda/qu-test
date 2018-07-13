package com.qualityunit.task.analyzer;

import com.qualityunit.task.entity.Record;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
public interface RecordMatcher<QUERY extends Record, IN_RECORD extends Record> {

    boolean match(QUERY query, IN_RECORD record);
}
