package com.qualityunit.task;

import com.qualityunit.task.analyzer.QueryAnalyzer;
import com.qualityunit.task.entity.Query;
import com.qualityunit.task.entity.WaitTimeLine;
import com.qualityunit.task.parser.RecordsParser;
import com.qualityunit.task.util.FilesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

public class AnalyticalTool {

    private static Logger LOGGER = LoggerFactory.getLogger(AnalyticalTool.class);

    public static final String ANY_PATTERN = "*";
    public static final String TYPE_PATTERN = "(?<type>[CD])";
    public static final String SERVICE_PATTERN = String.format("(?<service>[0-9]+\\.?[0-9]*|\\%s)", ANY_PATTERN);
    public static final String QUESTION_PATTERN = String.format("(?<question>[0-9]+\\.?[0-9]*\\.?[0-9]*+|\\%s)", ANY_PATTERN);
    public static final String RESPONSE_TYPE_PATTERN = "(?<answer>[PN])";
    public static final String DATE_FROM_PATTERN = "(?<dateFrom>\\d{1,2}.\\d{2}.\\d{4})";
    public static final String DATE_TO_PATTERN = "-?(?<dateTo>(\\d{1,2}\\.\\d{2}\\.\\d{4}))?";
    public static final String TIME_PATTERN = "\\s?(?<time>\\d+)";

    public static final String RECORD_PATTERN;
    public static final String WAIT_TIME_LINE_PATTERN;
    public static final String QUERY_PATTERN;

    static {
        RECORD_PATTERN = String.join("\\s?", TYPE_PATTERN,
                SERVICE_PATTERN, QUESTION_PATTERN, RESPONSE_TYPE_PATTERN, DATE_FROM_PATTERN);

        WAIT_TIME_LINE_PATTERN = RECORD_PATTERN + TIME_PATTERN;
        QUERY_PATTERN = RECORD_PATTERN + DATE_TO_PATTERN;

//        BasicConfigurator.configure();
    }

    private static String ORIGINAL_FILE_PATH;

    public static void main(String[] args) {
        parseArgs(args);

        AnalyticalTool tool = new AnalyticalTool();
        QueryAnalyzer analyzer = new QueryAnalyzer();
        StringWriter writer = new StringWriter();
        writer.append("\n");

        tool.analyzeAndWrite(analyzer, writer);
        LOGGER.info(writer.toString());

    }

    private static void parseArgs(String[] args) {
        if (args != null && args.length >= 1) {
            ORIGINAL_FILE_PATH = args[0];
        } else {
            LOGGER.warn("Usage: java -jar analytical-tool-1.0.jar <input_origin_file_path>");
            System.exit(-1);
        }
    }

    private void analyzeAndWrite(QueryAnalyzer analyzer, Writer writer) {
        List<String> lines = FilesUtil.getLinesFromFile(ORIGINAL_FILE_PATH);
        lines.remove(0);

        RecordsParser parser = new RecordsParser();
        List<WaitTimeLine> waitTimeLines = new LinkedList<>();
        parser.parseRecordsAsStream(lines)
                .forEach(line -> {
                    if (line instanceof WaitTimeLine) {
                        waitTimeLines.add((WaitTimeLine) line);
                    } else if (line instanceof Query) {
                        writeResult(analyzer, writer, waitTimeLines, (Query) line);
                    }
                });

    }

    private void writeResult(QueryAnalyzer analyzer, Writer writer, List<WaitTimeLine> waitTimeLines, Query line) {
        Double averageWaitingTime = analyzer.getAverageWaitingTime(line, waitTimeLines);
        try {
            if (averageWaitingTime > 0) {
                writer.write(String.valueOf(Math.round(averageWaitingTime)));
                writer.write("\n");
            } else {
                writer.write("-\n");
            }
        } catch (IOException e) {
            LOGGER.error("An I/O error occurs while using [{}] writer ", writer.toString(), e);
        }
    }
}
