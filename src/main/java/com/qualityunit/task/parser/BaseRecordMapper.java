package com.qualityunit.task.parser;

import com.qualityunit.task.entity.Question;
import com.qualityunit.task.entity.Record;
import com.qualityunit.task.entity.Service;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qualityunit.task.AnalyticalTool.ANY_PATTERN;
import static com.qualityunit.task.AnalyticalTool.RECORD_PATTERN;

/**
 * Created by Sergiy Dyrda on 15.07.2018
 */
public class BaseRecordMapper implements RecordMapper<Record> {

    private Pattern pattern;

    public BaseRecordMapper(String recordPattern) {
        this.pattern = Pattern.compile(recordPattern);
    }

    public BaseRecordMapper() {
        this(RECORD_PATTERN);
    }

    protected Pattern getPattern() {
        return pattern;
    }

    protected void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Record apply(String strRecord) {
        Matcher matcher = pattern.matcher(strRecord);
        matcher.matches();

        Record record = new Record();
        String serviceGroup = getGroup(matcher, "service");
        Service service = parseService(serviceGroup);
        record.setService(service);

        String questionGroup = getGroup(matcher, "question");
        Question question = parseQuestion(questionGroup);
        record.setQuestion(question);

        String answer = getGroup(matcher, "answer");
        record.setResponseType(Record.ResponseType.of(answer));

        String dateGroup = getGroup(matcher, "dateFrom");
        LocalDate date = parseDate(dateGroup);
        record.setDate(date);

        return record;
    }

    protected String getGroup(Matcher matcher, String groupName) {
        return matcher.group(groupName);
    }

    private Service parseService(String serviceGroup) {
        Service service;
        if (serviceGroup.equals(ANY_PATTERN))
            service = new Service.AnyService();
        else {
            service = new Service();
            String[] serviceParams = serviceGroup.split("\\.");
            if (serviceParams.length >= 1)
                service.setId(serviceParams[0]);
            if (serviceParams.length >=  2)
                service.setVariationId(serviceParams[1]);
        }
        return service;
    }

    private Question parseQuestion(String questionGroup) {
        Question question;
        if (questionGroup.equals(ANY_PATTERN))
            question = new Question.AnyQuestion();
        else {
            question = new Question();
            String[] questionParams = questionGroup.split("\\.");
            if (questionParams.length >= 1)
                question.setId(questionParams[0]);
            if (questionParams.length >=  2)
                question.setCategoryId(questionParams[1]);
            if (questionParams.length >= 3)
                question.setSubcategoryId(questionParams[2]);
        }
        return question;
    }

    protected LocalDate parseDate(String dateGroup) {
        if (dateGroup == null || dateGroup.isEmpty())
            return null;

        String[] date = dateGroup.split("\\.");
        return LocalDate.of(
                Integer.parseInt(date[2]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[0])
        );
    }
}
