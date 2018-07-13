package com.qualityunit.task.analyzer;

import com.qualityunit.task.entity.Query;
import com.qualityunit.task.entity.WaitTimeLine;

import java.util.List;
import java.util.stream.Collectors;

import static com.qualityunit.task.util.DateUtil.isBetween;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */

public class QueryAnalyzer extends RecordsAnalyzer<Query, WaitTimeLine> {


    private static final RecordMatcher<Query, WaitTimeLine> DEFAULT_QUERY_MATCHER = (query, waitTimeLine) ->
            query.getService().equals(waitTimeLine.getService()) &&
                    query.getQuestion().equals(waitTimeLine.getQuestion()) &&
                    query.getResponseType() == waitTimeLine.getResponseType() &&
                    isBetween(waitTimeLine.getDate(), query.getDate(), query.getDateTo());


    public QueryAnalyzer(RecordMatcher<Query, WaitTimeLine> recordMatcher) {
        super(recordMatcher);
    }

    public QueryAnalyzer() {
        this(DEFAULT_QUERY_MATCHER);
    }

    @Override
    public List<WaitTimeLine> getSuitableRecords(Query query, List<WaitTimeLine> records, RecordMatcher<Query, WaitTimeLine> matcher) {
        return records.stream().filter(wtl -> matcher.match(query, wtl))
                .collect(Collectors.toList());
    }


    @Override
    public List<WaitTimeLine> getSuitableRecords(Query query, List<WaitTimeLine> waitTimeLines) {
        return getSuitableRecords(query, waitTimeLines, DEFAULT_QUERY_MATCHER);
    }
}
