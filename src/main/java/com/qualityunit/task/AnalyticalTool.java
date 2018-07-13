package com.qualityunit.task;

public class AnalyticalTool {

    public static final String TYPE_PATTERN = "(?<type>[CD])";
    public static final String SERVICE_PATTERN = "(?<service>[0-9]+\\.?[0-9]*|\\*)";
    public static final String QUESTION_PATTERN = "(?<question>[0-9]+\\.?[0-9]*\\.?[0-9]*+|\\*)";
    public static final String ANSWER_PATTERN = "(?<answer>[PN])";
    public static final String DATE_FROM_PATTERN = "(?<dateFrom>\\d{1,2}.\\d{2}.\\d{4})";
    public static final String DATE_TO_PATTERN = "(?<dateTo>-\\d{1,2}.\\d{2}.\\d{4})?";
    public static final String TIME_PATTERN = "(?<time>\\d+)";

    public static final String RECORD_PATTERN;

    static {
        RECORD_PATTERN = String.join("\\s?", TYPE_PATTERN,
                SERVICE_PATTERN, QUESTION_PATTERN, ANSWER_PATTERN, DATE_FROM_PATTERN,
                String.format("(?:%s|%s)", DATE_TO_PATTERN, TIME_PATTERN));
    }

    public static void main(String[] args) {

    }
}
