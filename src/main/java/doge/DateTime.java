package doge;

import doge.exception.DogeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents a class for parsing Date and Time
 */
public class DateTime {
    /**
     * Returns a LocalDateTime when given a string input of a date and time.
     *
     * @param input a string input of date and time
     * @return the date and time of LocalDateTime type
     * @throws DogeException if date/time given does not exist or is of an incorrect format
     */
    public static LocalDateTime getDateTime(String input) throws DogeException {
        LocalDateTime currDateTime = LocalDateTime.now();

        try {
            String[] temp = input.trim().split(" ");
            String[] tempTime = temp[1].split(":");
            LocalDate date = LocalDate.parse(temp[0]);
            LocalTime time = LocalTime.of(Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
            LocalDateTime inputDateTime = LocalDateTime.of(date, time);

            if (inputDateTime.isAfter(currDateTime)) {
                return inputDateTime;
            } else {
                throw new DateTimeException("Invalid date/time!");
            }
        } catch (DateTimeException e) {
            throw new DogeException("Are you lacking common sense? Invalid date/time!");
        }
    }
}
