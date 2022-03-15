package chatbot.datetime;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.ResolverStyle;

import chatbot.exception.ChatBotException;

//TODO break this up

/**
 * Represents a date or a combination of date and time.
 */
public class Timestamp {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/uuuu"
            ).withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Instantiates a new Timestamp.
     *
     * @param input The input given by the user.
     * @throws ChatBotException If user input is of an invalid format.
     */
    public Timestamp(String input) throws ChatBotException {
        String[] splitInput = input.split(" ");
        String dateString = splitInput[0];

        if (splitInput.length > 2) {
            throw new ChatBotException("That's an invalid timestamp format traveller!");
        }

        try {
            this.date = generateDate(dateString);
        } catch (DateTimeException e) {
            throw new ChatBotException("That's an invalid date format traveller!");
        }
        if (splitInput.length == 2) {
            String timeString = splitInput[1];
            try {
                this.time = generateTime(timeString);
            } catch (DateTimeException e) {
                throw new ChatBotException("That's an invalid time format traveller!");
            }
        } else {
            this.time = null;
        }
    }

    /**
     * Gets date.
     *
     * @return The date of this Timestamp.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets time.
     *
     * @return The time of this Timestamp.
     */
    public LocalTime getTime() {
        return time;
    }

    private static LocalDate generateDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }

    private static LocalTime generateTime(String timeString) {
        return LocalTime.parse(timeString, TIME_FORMATTER);
    }

    /**
     * Convert Timestamp into a String format appropriate for being saved in the save file.
     *
     * @return The formatted save string.
     */
    public String toSaveString() {
        if (date == null) {
            return "";
        }
        String saveString = date.format(DATE_FORMATTER);
        if (time != null) {
            saveString = String.format("%s %s", saveString, time.format(TIME_FORMATTER));
        }
        return saveString;
    }

    @Override
    public String toString() {
        if (date == null) {
            return "";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
                    "dd MMMM uuuu"
            );
        String res = date.format(dateFormatter);
        if (time != null) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(
                    FormatStyle.SHORT
            );
            String timeString = time.format(timeFormatter);
            res = String.format("%s, %s", res, timeString);
        }
        return res;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Timestamp) {
            Timestamp dt = (Timestamp) other;
            LocalDate otherDate = dt.getDate();
            if (otherDate != null) {
                return otherDate.equals(date);
            }
        }
        return false;
    }
}
