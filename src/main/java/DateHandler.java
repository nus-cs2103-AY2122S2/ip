/**
 * This file contains the implementation of DatHandler class.
 * @author  Saravanan Anuja Harish
 */

import java.time.LocalDate;
import java.time.LocalTime;

public class DateHandler {

    // Separator to split deadline substring.
    private static final String SPACE = " ";

    // stores an empty string.
    private static final String EMPTY = "";

    // Limits the splitting of date into substrings.
    private static final int LIMIT = 2;

    // The starting index of a character in a string.
    private static final int START_INDEX = 0;

    // The index value of date string in split array.
    private static final int DATE_IDX = 0;

    // The index value of time string in split array.
    private static final int TIME_IDX = 1;

    // The index value where minute character starts in time string.
    private static final int MINUTE_INDEX = 2;

    // stores information on the date.
    private final LocalDate date;

    // stores information on the time.
    private final boolean isTimeGiven;

    // stores the length of date argument.
    private static final int DATE_LENGTH = 10;

    // stores the length of time argument.
    private static final int TIME_LENGTH = 4;

    // stores the time argument.
    private final LocalTime time;

    /**
     * Constructor for DateHandler class.
     * @param date the string representation of the date.
     * returns an instance of DateHandler.
     */
    DateHandler(String date) {
        date = date.trim();
        if (date.contains(SPACE)) {
            this.date = LocalDate.parse(date.split(SPACE, LIMIT)[DATE_IDX]);
            String hour = date.split(SPACE, LIMIT)[TIME_IDX].substring(START_INDEX, MINUTE_INDEX);
            String minute = date.split(SPACE, LIMIT)[TIME_IDX].substring(MINUTE_INDEX);
            this.time = LocalTime.of(Integer.valueOf(hour), Integer.valueOf(minute));
            this.isTimeGiven = true;
        } else {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.MIN;
            this.isTimeGiven = false;
        }
    }

    /**
     * checks if the userDate is of the valid format.
     * @param userDate the date input by user.
     * @throw WrongDateArgumentException if the user inputs an invalid date.
     * @throw WrongTimeArgumentException if the user inputs an invalid time.
     */
    static void checkValidDate(String userDate) {

        // handle time checking
        if (userDate.contains(SPACE)) {
            String hr_mt = userDate.split(SPACE, LIMIT)[TIME_IDX].trim();

            if (hr_mt.length() != TIME_LENGTH) {
                throw new WrongTimeArgumentException(userDate);
            }

            for (int i = 0; i < TIME_LENGTH; i++) {
                if (!Character.isDigit(hr_mt.charAt(i))) {
                    throw new WrongTimeArgumentException(userDate);
                }
            }

            userDate = userDate.substring(START_INDEX, DATE_LENGTH).trim();
        }

        // handles date checking.
        char separator = "-".charAt(START_INDEX);
        int firstSeparatorIdx = 4;
        int secondSeparatorIdx = 7;

        // checks if the date is in the correct format.
        if (userDate.length() != DATE_LENGTH) {
            throw new WrongDateArgumentException(userDate);
        }

        if (userDate.charAt(firstSeparatorIdx) != separator || userDate.charAt(secondSeparatorIdx) != separator) {
            throw new WrongDateArgumentException(userDate);
        }

        for (int i = 0; i < DATE_LENGTH; i++) {
            if (i != firstSeparatorIdx && i != secondSeparatorIdx) {
                if (!Character.isDigit(userDate.charAt(i))) {
                    throw new WrongDateArgumentException(userDate);

                }
            }
        }
    }

    /**
     * checks whether the date is before the given date.
     * @param maxDate the value if the date limit
     * @return true if the true; false otherwise
     */
    boolean isBefore(String maxDate) {
        return this.date.isBefore(LocalDate.parse(maxDate.trim()));
    }

    /**
     * checks if the date is same.
     * @param onDate the date given.
     * @return return true if onDate matches the date of instance; false otherwise.
     */
    boolean isOnDate(String onDate) {
        return this.date.equals(LocalDate.parse(onDate.trim()));
    }

    /**
     * returns the string representation of the DateHandler instance.
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return this.date.getDayOfWeek() + SPACE + (this.isTimeGiven ? this.time + SPACE : EMPTY) +
                this.date.getDayOfMonth() + SPACE + this.date.getMonth() + SPACE + this.date.getYear();
    }

}
