package helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import exceptions.WrongDateArgumentException;
import exceptions.WrongTimeArgumentException;

/**
 * <h1>DateHandler</h1>
 * <p>
 * DateHandler task handles the operation related to the dates associated with tasks.
 * </p>
 *
 * @author  Saravanan Anuja Harish
 */
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

    // stores the length of date argument.
    private static final int DATE_LENGTH = 10;

    // stores the length of time argument.
    private static final int TIME_LENGTH = 4;

    // stores information on the date.
    private final LocalDate date;

    // stores information on the time.
    private final boolean isTimeGiven;

    // stores the time argument.
    private final LocalTime time;

    /**
     * Constructor for DateHandler class.
     *
     * @param date the string representation of the date.
     * returns an instance of DateHandler.
     */
    public DateHandler(String date) {
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
     * Constructor for DateHandler.
     *
     * @param date the date associated with the task from the previous runs.
     * @param dummyVariable to differentiate between the constructors.
     */
    public DateHandler(String date, int dummyVariable) {

        String[] strings = date.split(SPACE);

        for (int i = 0; i < strings.length / 2; i++) {
            String temp = strings[i];
            strings[i] = strings[strings.length - i - 1];
            strings[strings.length - i - 1] = temp;
        }

        int yearIdx = 0;
        int monthIdx = 1;
        int dayIdx = 2;
        this.date = LocalDate.of(Integer.valueOf(strings[yearIdx]), Month.valueOf(strings[monthIdx]),
                Integer.valueOf(strings[dayIdx]));

        int splitArrLenWithTime = 5;
        if (strings.length == splitArrLenWithTime) {
            //extract time
            String[] time = strings[strings.length - 2].split(":");
            int hourIdx = 0;
            int minuteIdx = 1;
            int hour = Integer.valueOf(time[hourIdx]);
            int minute = Integer.valueOf(time[minuteIdx].substring(1));
            this.time = LocalTime.of(hour, minute);
            this.isTimeGiven = true;
        } else {
            this.time = LocalTime.MIN;
            this.isTimeGiven = false;
        }
    }

    /**
     * checks if the userDate is of the valid format.
     *
     * @param userDate the date input by user.
     * @throw WrongDateArgumentException if the user inputs an invalid date.
     * @throw WrongTimeArgumentException if the user inputs an invalid time.
     */
    public static void checkValidDate(String userDate) {

        // handle time checking
        if (userDate.contains(SPACE)) {
            String hourMinute = userDate.split(SPACE, LIMIT)[TIME_IDX].trim();

            if (hourMinute.length() != TIME_LENGTH) {
                throw new WrongTimeArgumentException(userDate);
            }

            for (int i = 0; i < TIME_LENGTH; i++) {
                if (!Character.isDigit(hourMinute.charAt(i))) {
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
     *
     * @param maxDate the value if the date limit.
     * @return true if the true; false otherwise.
     */
    public boolean isBefore(String maxDate) {
        return this.date.isBefore(LocalDate.parse(maxDate.trim()));
    }

    /**
     * checks if the date is same.
     *
     * @param onDate the date given.
     * @return return true if onDate matches the date of instance; false otherwise.
     */
    public boolean isOnDate(String onDate) {
        return this.date.equals(LocalDate.parse(onDate.trim()));
    }

    /**
     * returns the string representation of the DateHandler instance.
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return this.date.getDayOfWeek() + SPACE + (this.isTimeGiven ? this.time + SPACE : EMPTY)
                + this.date.getDayOfMonth() + SPACE + this.date.getMonth() + SPACE + this.date.getYear();
    }

}
