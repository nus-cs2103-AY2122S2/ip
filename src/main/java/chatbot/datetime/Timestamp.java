package chatbot.datetime;

import chatbot.exception.ChatBotException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a date or a combination of date and time.
 */
public class Timestamp {

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Instantiates a new Timestamp.
     *
     * @param input The input given by the user.
     * @throws ChatBotException If user input is of an invalid format.
     */
    public Timestamp(String input) throws ChatBotException {
        String[] firstSplit = input.split(" ");
        String dateHalf = firstSplit[0];
        String[] secondSplit = dateHalf.split("/");
        if (secondSplit.length == 3) {
            try {
                this.date = generateDate(secondSplit);
            } catch (DateTimeException | NumberFormatException e) {
                throw new ChatBotException(
                        "That's an invalid date format traveller!"
                );
            }
            if (firstSplit.length == 2) {
                String timeString = firstSplit[1];
                if (timeString.length() == 4) {
                    try {
                        this.time = generateTime(timeString);
                    } catch (DateTimeException | NumberFormatException e) {
                        throw new ChatBotException(
                                "That's an invalid time format traveller!"
                        );
                    }
                } else {
                    throw new ChatBotException(
                            "That's an invalid time format traveller!"
                    );
                }
            } else {
                this.time = null;
            }
        } else {
            throw new ChatBotException(
                    "That's an invalid timestamp format traveller!"
            );
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

    private static LocalDate generateDate(String[] dateString) {
        int day = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);
        return LocalDate.of(year, month, day);
    }

    private static LocalTime generateTime(String timeString) {
        int hour = Integer.parseInt(timeString.substring(0, 2));
        int minute = Integer.parseInt(timeString.substring(2));
        return LocalTime.of(hour, minute);
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

    /**
     * Convert Timestamp into a String format appropriate for being saved in the save file.
     *
     * @return The formatted save string.
     */
    public String saveString() {
        if (date != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy"
            );
            String dateString = date.format(dateFormatter);
            if (time != null) {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(
                        "HHmm"
                );
                String timeString = time.format(timeFormatter);
                return String.format("%s %s", dateString, timeString);
            } else {
                return dateString;
            }
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        if (date != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
                    "dd MMMM yyyy"
            );
            String dateString = date.format(dateFormatter);
            if (time != null) {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(
                        FormatStyle.SHORT
                );
                String timeString = time.format(timeFormatter);
                return String.format("%s, %s", dateString, timeString);
            } else {
                return dateString;
            }
        } else {
            return "";
        }
    }
}
