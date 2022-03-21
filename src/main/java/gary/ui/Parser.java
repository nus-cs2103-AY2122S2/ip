package gary.ui;

import gary.exception.GaryException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helps to decode the user input and
 * invoke the corresponding operation to be done
 */
public class Parser {

    /**
     * Parse user input
     *
     * @param input User input
     * @param tsk TaskList to be used
     * @return indication to end of user input
     */
    public String parse(String input, TaskList tsk) throws GaryException {
        assert !input.isBlank(); // catches empty input
        try {
            String[] type = input.split(" ");
            String theTask = type[0];
            switch (theTask) {
                case "bye":
                    return "Bye, have a productive day!";
                case "hi":
                case "hello":
                case "hey":
                    return theTask + "! Enter <help> if you're unfamiliar with me!";
                case "todo":
                    return tsk.addTodo(input.substring(5));
                case "event":
                    String[] e = input.split("/", 5);
                    return tsk.addEvent(e[0].substring(6), parseDate(e));
                case "deadline":
                    String[] d = input.split("/", 5);
                    return tsk.addDeadline(d[0].substring(9), parseDate(d));
                case "find":
                    return tsk.find(input.substring(5));
            }
        } catch (StringIndexOutOfBoundsException e) {
            return "Ah please enter a valid description e.g. task_type name / date";
        } catch (DateTimeParseException e) {
            return "Ah please enter a valid date e.g. 19-01-2022,2359";
        }
        return tsk.invoke(input);
    }

    /**
     * Convert representation of date and time
     * e.g. from 19-01-2001,2359 to 19 Jan 2001 23:59pm
     *
     * @param d User input
     * @return date and time
     */
    private static String parseDate(String[] d) {
        DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy,HHmm");
        LocalDateTime d1 = LocalDateTime.parse(d[1].strip(), inFormat);
        DateTimeFormatter outFormat = DateTimeFormatter.ofPattern("dd LLL yyyy HH:mm a");
        return d1.format(outFormat);
    }
}
