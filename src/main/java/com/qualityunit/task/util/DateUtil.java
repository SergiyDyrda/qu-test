package com.qualityunit.task.util;

import java.time.LocalDate;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
public class DateUtil {

    public static boolean isBetween(LocalDate ld, LocalDate startDate, LocalDate endDate) {
        return ld.compareTo(startDate) >= 0 && ld.compareTo(endDate) <= 0;
    }

}
