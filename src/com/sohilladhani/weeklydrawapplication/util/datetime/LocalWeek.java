/* Help from : https://stackoverflow.com/questions/22890644/get-current-week-start-and-end-date-in-java-monday-to-sunday
 * http://www.java2s.com/Tutorials/Java/Data_Type_How_to/Date/Get_current_week_start_and_end_date_MONDAY_TO_SUNDAY_.htm
 * */

package com.sohilladhani.weeklydrawapplication.util.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalWeek {

    private static ZoneId timeZone = ZoneId.of("America/Guyana");

    public static String getCurrentWeek() {
        LocalDate today = LocalDate.now(timeZone);
        LocalDate firstDayOfTheWeek = today;

        while (firstDayOfTheWeek.getDayOfWeek() != DayOfWeek.SUNDAY) {
            firstDayOfTheWeek = firstDayOfTheWeek.minusDays(1);
        }

        LocalDate lastDayOfTheWeek = today;
        while (lastDayOfTheWeek.getDayOfWeek() != DayOfWeek.SATURDAY) {
            lastDayOfTheWeek = lastDayOfTheWeek.plusDays(1);
        }

        return firstDayOfTheWeek.format(DateTimeFormatter.ofPattern("MMM D")) +
                " - " +
                lastDayOfTheWeek.format(DateTimeFormatter.ofPattern("MMM D"));
    }

}
