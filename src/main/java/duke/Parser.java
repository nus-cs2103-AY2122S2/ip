package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that parses user input and returns
 * command type of input
 */
public class Parser {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Ui.DATE_TIME_FORMAT);
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Ui.TIME_FORMAT);

    /**
     * Reads user input and returns command type detected
     *
     * @param input User input text
     * @return Command type
     */
    public static Command parse(String input) {
        String[] inputSplit = input.split(" ");

        if (inputSplit.length > 0) {
            String commandString = inputSplit[0];

            if (commandString.equals("bye")) {
                return Command.BYE;
            } else if (commandString.equals("list")) {
                return Command.LIST;
            } else if (commandString.equals("mark")) {
                return Command.MARK;
            } else if (commandString.equals("unmark")) {
                return Command.UNMARK;
            } else if (commandString.equals("deadline")) {
                return Command.DEADLINE;
            } else if (commandString.equals("event")) {
                return Command.EVENT;
            } else if (commandString.equals("todo")) {
                return Command.TODO;
            } else if (commandString.equals("delete")) {
                return Command.DELETE;
            } else if (commandString.equals("find")) {
                return Command.FIND;
            } else {
                return Command.ERROR;
            }
        } else {
            return Command.ERROR;
        }
    }

    public static LocalDateTime parseTime(String time) {
        return LocalDateTime.parse(time, timeFormatter);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    /**
     * Extract start and end date timings
     *
     * @param dateTimingSplit Event date timing string array containing start date time and end time
     * @return Array of event start date time and end date time in format "2/12/2022 1800"
     */
    private static String[] extractEventDateTimings(String[] dateTimingSplit) {
        // "2/12/2022"
        String dateText = dateTimingSplit[0].trim().split(" ")[0];
        // "1800"
        String startTimeText = dateTimingSplit[0].trim().split(" ")[1];
        // "1900"
        String endTimeText = dateTimingSplit[1].trim();
        // "2/12/2022 1800"
        String startDateText = dateText + " " + startTimeText;
        // "2/12/2022 1900"
        String endDateText = dateText + " " + endTimeText;
        // ["2/12/2022 1800", "2/12/2022 1900"]
        String[] eventTimings = new String[] {startDateText, endDateText};
        return eventTimings;
    }

    public static String getEventTimings(String text) {
        // original EVENT XXX /at 2/12/2022 1800 to 1900

        // ["EVENT XXX", "2/12/2022 1800 to 1900"]
        String fullDateString = text.split("/at")[1].trim();
        // ["2/12/2022 1800", "1900"]
        String[] timingSplit = fullDateString.split("to");
        String[] eventTimings = extractEventDateTimings(timingSplit);

        // if user input does NOT contain start and end timing
        // in the given format
        if (eventTimings.length < 2) {
            return Ui.EVENT_INVALID_TIMINGS_ERROR;
        } else {
            return String.join(",", eventTimings);
        }
    }

    public static String getDeadlineTiming(String text) {
        String[] inputSplit = text.split("/by");

        if (inputSplit.length < 2) {
            return Ui.DEADLINE_INVALID_TIMINGS_ERROR;
        }

        String dateText = inputSplit[1].trim();

        try {
            LocalDateTime endDate = parseDateTime(dateText);
            return dateText;
        } catch (DateTimeParseException err) {
            return Ui.DEADLINE_INVALID_TIMINGS_ERROR;
        }
    }
}
