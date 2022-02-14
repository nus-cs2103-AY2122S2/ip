package date.time;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;

import exceptions.InvalidDateException;
import exceptions.InvalidInputException;
import exceptions.InvalidTimeException;
import exceptions.NotLeapYearException;

/**
 * This class encapsulates a Date Time Parser. It contains methods to parse inputs into a
 * LocalDateTime object, as well as a checker for the format.
 *
 * @author Ong Han Yang
 */
public final class DateTimeParser {
    /** Reusable Invalid Input Exception for when the input command has an invalid date format */
    public static final InvalidInputException INVALID_DATE_TIME_FORMAT =
            new InvalidInputException("The date/time format is wrong! Please enter in this format: yyyy-mm-dd hh:mm");

    /** The indexes for a properly formatted date-time string input. Corresponds to yyyy-mm-dd hh:mm. */
    private static final int INDEX_DASH_ONE = 4;
    private static final int INDEX_DASH_TWO = 7;
    private static final int INDEX_SPACE = 10;
    private static final int INDEX_COLON = 13;

    /**
     * Parses a string input in the form yyyy-mm-dd hh:mm, where dd is the day, mm is the month,
     * yyyy is the year and hh:mm is the 24 hour time, to produce a LocalDateTime object
     * with the provided date and time.
     *
     * Does not check for invalid date/time.
     *
     * @param input the string input in the form yyyy-mm-dd xx:xx .
     * @return the LocalDateTime corresponding to the input.
     */
    public static LocalDateTime parse(String input) {
        String[] split = input.split(" ", 2);
        int[] date = Arrays.stream(split[0]
                .split("-", 3))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] time = Arrays.stream(split[1]
                .split(":", 2))
                .mapToInt(Integer::parseInt)
                .toArray();
        return LocalDateTime.of(
                date[0],
                date[1],
                date[2],
                time[0],
                time[1]);
    }

    /**
     * Parses a string in the format of {@code hh:mm} to produce a LocalTime object with the specified
     * hour and minute.
     *
     * Does not check for invalid time.
     *
     * @param input a String in the form of {@code hh:mm}.
     * @return a LocalTime object with the specified time.
     */
    public static LocalTime parseTime(String input) {
        String[] split = input.split(":", 2);
        return LocalTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    /**
     * Checks if a given string input has a valid format to be converted into
     * a LocalDateTime using the DateTimeParser::parse method.
     *
     * @param input The string to be checked if it follows yyyy-mm-dd hh:mm .
     * @return true if there are no exceptions.
     * @throws InvalidInputException when a given format is wrong.
     */
    public static boolean checkValidFormat(String input) throws InvalidInputException {
        boolean isCorrectSeparators = checkSeparatorsAndDigits(input);
        String[] split = input.split(" ", 2);
        assert split.length == 2; // [date, time]
        boolean isValidDate = checkValidDate(split[0]);
        boolean isValidTime = checkValidTime(split[1]);
        return isCorrectSeparators && isValidDate && isValidTime;
    }

    /**
     * Checks if the separators and digits of the input are correct for the DateTimeParser::parse method.
     *
     * @param dateTime the input date and time, of the format yyyy-mm-dd hh:mm.
     * @return true when the given input has the right separators and are digits.
     * @throws InvalidInputException when the input has something wrong with separators and digits.
     */
    private static boolean checkSeparatorsAndDigits(String dateTime) throws InvalidInputException {
        if (dateTime.length() != 16) {
            throw INVALID_DATE_TIME_FORMAT;
        }
        for (int i = 0; i < dateTime.length(); i++) {
            if (i == INDEX_DASH_ONE || i == INDEX_DASH_TWO) {
                if (dateTime.charAt(i) != '-') {
                    throw INVALID_DATE_TIME_FORMAT;
                }
            } else if (i == INDEX_SPACE) {
                if (dateTime.charAt(10) != ' ') {
                    throw INVALID_DATE_TIME_FORMAT;
                }
            } else if (i == INDEX_COLON) {
                if (dateTime.charAt(13) != ':') {
                    throw INVALID_DATE_TIME_FORMAT;
                }
            } else { // for non divider indexes
                if (!Character.isDigit(dateTime.charAt(i))) {
                    throw INVALID_DATE_TIME_FORMAT;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the given input date is a valid date. If the input is invalid, an InvalidDateException will
     * be thrown, with an appropriate message.
     *
     * @param date the date, in the form of yyyy-mm-dd.
     * @return true when the given input is valid.
     * @throws InvalidDateException when the given input is invalid.
     */
    private static boolean checkValidDate(String date) throws InvalidDateException {
        String[] split = date.split("-", 3);
        assert split.length == 3;

        int years = Integer.parseInt(split[0]);
        int months = Integer.parseInt(split[1]);
        int days = Integer.parseInt(split[2]);

        boolean isLeapYear = Year.of(years).isLeap();
        boolean isValidMonths = months > 0 && months <= 12;
        boolean isValidDays = days > 0 && days <= 31;
        boolean isFeb29 = months == 2 && days == 29;

        if (isLeapYear && isFeb29) { // special case to deal with Feb29 on leap years
            return true;
        }
        if (!isLeapYear && isFeb29) {
            throw NotLeapYearException.get(years);
        }
        if (!isValidMonths) {
            throw new InvalidDateException(String.format("There is no %d-th month!", months));
        }
        if (!isValidDays) {
            throw new InvalidDateException(String.format("There is no %d-th day!", days));
        }

        Month monthObject = Month.of(months);
        boolean isValidDayGivenMonth = monthObject.minLength() >= days;

        if (!isValidDayGivenMonth) {
            throw new InvalidDateException(String.format("%s does not have a %d-th day!", monthObject, days));
        }
        return true;
    }

    /**
     * Checks if the given input time is a valid time. If the input is invalid, an InvalidDateException will
     * be thrown, with an appropriate message.
     *
     * @param time the time, in the form of hh:mm
     * @return true when the given input is valid.
     * @throws InvalidTimeException when the given input is invalid.
     */
    private static boolean checkValidTime(String time) throws InvalidTimeException {
        String[] split = time.split(":", 2);
        assert split.length == 2;

        int hours = Integer.parseInt(split[0]);
        int mins = Integer.parseInt(split[1]);

        boolean isValidHours = hours >= 0 && hours < 24;
        boolean isValidMins = mins >= 0 && mins < 60;

        if (!isValidHours) {
            throw new InvalidTimeException(String.format("There is no %d-th hour!", hours));
        }
        if (!isValidMins) {
            throw new InvalidTimeException(String.format("There is no %d-th minute!", mins));
        }
        return true;
    }

}
