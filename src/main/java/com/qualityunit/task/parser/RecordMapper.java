package com.qualityunit.task.parser;

import com.qualityunit.task.entity.Record;

import java.util.function.Function;

/**
 * Created by Sergiy Dyrda on 15.07.2018
 */
public interface RecordMapper<R extends Record> extends Function<String, R> {
}
