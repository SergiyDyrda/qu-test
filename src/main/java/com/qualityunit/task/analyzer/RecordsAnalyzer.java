package com.qualityunit.task.analyzer;

import com.qualityunit.task.entity.Record;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.function.Supplier;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
public abstract class RecordsAnalyzer<QUERY extends Record, IN_RECORD extends Record> {

    private RecordMatcher<QUERY, IN_RECORD> recordMatcher;

    public RecordsAnalyzer(RecordMatcher<QUERY, IN_RECORD> recordMatcher) {
        this.recordMatcher = recordMatcher;
    }

    protected RecordMatcher<QUERY, IN_RECORD> getRecordMatcher() {
        return recordMatcher;
    }


    public List<IN_RECORD> getSuitableRecords(QUERY query, Collection<IN_RECORD> records) {
        return getSuitableRecords(query, records, LinkedList::new);
    }

    protected <T extends Collection<IN_RECORD>> T getSuitableRecords(QUERY query, Collection<IN_RECORD> records, Supplier<T> collectionSupplier) {
        return getSuitableStreamOfRecords(query, records)
                .collect(Collectors.toCollection(collectionSupplier));
    }

    protected LongSummaryStatistics getStatisticsByField(QUERY query, Collection<IN_RECORD> records, ToLongFunction<IN_RECORD> mapper) {
        return getSuitableStreamOfRecords(query, records)
                .collect(Collectors.summarizingLong(mapper));
    }

    private Stream<IN_RECORD> getSuitableStreamOfRecords(QUERY query, Collection<IN_RECORD> records) {
        return records.stream().filter(wtl -> recordMatcher.match(query, wtl));
    }
}