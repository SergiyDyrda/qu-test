package com.qualityunit.task.parser;

import com.qualityunit.task.TestData;
import com.qualityunit.task.entity.Record;
import com.qualityunit.task.util.FilesUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecordsParserTest {

    private RecordsParser parser = new RecordsParser();

    @Test
    public void testParseRecordsFromFile() {

        List<String> linesFromFile = FilesUtil.getLinesFromClasspathFile("records.txt", getClass());
        linesFromFile.remove(0);
        List<Record> records = parser.parseRecords(linesFromFile);
        assertEquals(TestData.allRecords, records);
    }
}