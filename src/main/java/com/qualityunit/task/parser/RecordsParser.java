package com.qualityunit.task.parser;

import com.qualityunit.task.entity.Record;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Sergiy Dyrda on 15.07.2018
 */
public class RecordsParser {


    public List<Record> parseRecords(List<String> lines) {
        return parseRecordsAsStream(lines)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public Stream<Record> parseRecordsAsStream(List<String> lines) {
        return lines.stream().map(this::mapping);
    }

    private Record mapping(String line) {
        RecordMapper<Record> mapper;
        line = line.trim();
        if (line.startsWith("C"))
            mapper = new WaitTimeLineMapper();
        else if (line.startsWith("D"))
            mapper = new QueryMapper();
        else
            mapper = s -> new Record();

        return mapper.apply(line);
    }

}
