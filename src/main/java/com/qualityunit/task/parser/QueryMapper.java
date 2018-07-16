package com.qualityunit.task.parser;

import com.qualityunit.task.entity.*;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qualityunit.task.AnalyticalTool.QUERY_PATTERN;

/**
 * Created by Sergiy Dyrda on 15.07.2018
 */
public class QueryMapper extends BaseRecordMapper {

    {
        Pattern pattern = Pattern.compile(QUERY_PATTERN);
        setPattern(pattern);
    }

    @Override
    public Query apply(String strRecord) {
        Query query = new Query(super.apply(strRecord));
        Matcher matcher = getPattern().matcher(strRecord);
        matcher.matches();

        query.setDateTo(
                parseDate(
                        getGroup(matcher, "dateTo")
                )
        );
        return query;
    }

    @Override
    protected LocalDate parseDate(String dateGroup) {
        LocalDate date = super.parseDate(dateGroup);
        if (date == null)
            date = LocalDate.now();

        return date;
    }
}
