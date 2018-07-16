package com.qualityunit.task.util;

import com.qualityunit.task.exception.NotFoundException;
import com.qualityunit.task.exception.NotReadableFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created by Sergiy Dyrda on 15.07.2018
 */
public class FilesUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(FilesUtil.class);

    public static List<String> getLinesFromClasspathFile(String pathToFile, Class aClass) {
        List<String> lines;
        try {
            lines = Files.readAllLines(getCheckedClasspathPath(pathToFile, aClass));
        } catch (IOException e) {
            LOGGER.warn("An I/O error occurs while reading [{}] file ", pathToFile, e);
            lines = Collections.emptyList();
        }

        return lines;
    }

    public static List<String> getLinesFromFile(String pathToFile) {
        List<String> lines;
        try {
            lines = Files.readAllLines(getCheckedPath(pathToFile));
        } catch (IOException e) {
            LOGGER.warn("An I/O error occurs while reading [{}] file ", pathToFile, e);
            lines = Collections.emptyList();
        }

        return lines;
    }

    private static Path getCheckedClasspathPath(String pathToFile, Class aClass) {
        checkNotEmpty(pathToFile);
        try {
            return Paths.get(requireNonNull(aClass.getClassLoader().getResource(pathToFile)).toURI());
        } catch (NullPointerException | URISyntaxException e) {
            LOGGER.error("An error occurs while getting [{}] file", pathToFile, e);
            throw new NotReadableFileException(String.format("File %s is not exists or closed to reading", pathToFile), e);
        }

    }

    private static Path getCheckedPath(String pathToFile) {
        checkNotEmpty(pathToFile);
        try {
            return Paths.get(pathToFile);
        } catch (InvalidPathException e) {
            LOGGER.error("An error occurs while getting [{}] file", pathToFile, e);
            throw new NotReadableFileException(String.format("File %s is not exists or closed to reading", pathToFile), e);
        }

    }

    private static void checkNotEmpty(String pathToFile) {
        if (pathToFile == null || pathToFile.isEmpty()) {
            throw new NotFoundException("Specified path is incorrect: " + pathToFile);
        }
    }



}
