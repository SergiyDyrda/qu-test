package com.qualityunit.task.parser;

import com.qualityunit.task.entity.WaitTimeLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qualityunit.task.AnalyticalTool.WAIT_TIME_LINE_PATTERN;

/**
 * Created by Sergiy Dyrda on 15.07.2018
 */
public class WaitTimeLineMapper extends BaseRecordMapper {

    {
        Pattern pattern = Pattern.compile(WAIT_TIME_LINE_PATTERN);
        setPattern(pattern);
    }

    @Override
    public WaitTimeLine apply(String strRecord) {
        WaitTimeLine line = new WaitTimeLine(super.apply(strRecord));
        Matcher matcher = getPattern().matcher(strRecord);
        matcher.matches();

        line.setWaitingTime(
                Long.parseLong(
                        getGroup(matcher, "time")
                )
        );
        return line;
    }
}
