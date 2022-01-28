package duke.helptool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * The type Parser.
 */
public class Parser {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Instantiates a new Parser.
     */
    public Parser() {

    }

    /**
     * Is valid date boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public static boolean isValidDate(String input) {
        try {
            LocalDateTime.parse(input, dateFormat);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    /**
     * Is empty boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public static boolean isEmpty(String input) {
        if (input.equals("")) {
            return true;
        }
        int curr = 0;
        while (curr < input.length()) {
            if (input.charAt(curr) != ' ') {
                return false;
            }
            curr++;
        }
        return true;
    }


    /**
     * Parse command.
     *
     * @param input the input
     * @return the command
     * @throws DukeException the duke exception
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            // detect bye
            return new ExitCommand();
        } else if (input.equals("list")) {
            // detect list
            return new ListCommand();
        } else if (input.startsWith("find")) {
            String findDetail = input.substring(5);
            if (isEmpty(findDetail)) {
                throw new DukeException("☹ OOPS!!! The description of a find cannot be empty.");
            }
            return new FindCommand(findDetail);

        } else {
            if (input.startsWith("mark") || input.startsWith("unmark") || input.startsWith("delete")) {
                boolean isDigit = !Character.isDigit(input.charAt(input.length() - 1));
                int markDigit = Character.getNumericValue(input.charAt(input.length() - 1));
                if (input.startsWith("mark")) {
                    // detect digit
                    if (isDigit) {
                        throw new DukeException("☹ OOPS!!! The mark cannot be done.");
                    }
                    return new MarkCommand(markDigit);
                }
                if (input.startsWith("unmark")) {
                    // detect unmark
                    if (isDigit) {
                        throw new DukeException("☹ OOPS!!! The unmark cannot be done.");
                    }
                    return new UnmarkCommand(markDigit);
                }
                if (input.startsWith("delete")) {
                    // detect delete
                    if (isDigit) {
                        throw new DukeException("☹ OOPS!!! The delete cannot be done.");
                    }
                    return new DeleteCommand(markDigit);
                }
            } else {
                // add task
                if (input.startsWith("todo")) {
                    // generate
                    String description = input.substring(5);
                    if (isEmpty(description)) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    return new AddCommand(description, "T");
                } else if (input.startsWith("deadline")) {
                    if (isEmpty(input.substring(8))) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (!input.contains("/by")) {
                        throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                    }
                    int byPos = input.indexOf("/by");
                    String description = input.substring(9, byPos);
                    String by = input.substring(byPos + 4);
                    if (isValidDate(by)) {
                        LocalDateTime dateTime = LocalDateTime.parse(by, dateFormat);
                        return new AddCommand(description, "D", dateTime);
                    } else {
                        throw new DukeException("☹ OOPS!!! The date time is not of correct format dd/MM/yyyy HHmm.");
                    }
                } else if (input.startsWith("event")) {
                    if (isEmpty(input.substring(5))) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    if (!input.contains("/at")) {
                        throw new DukeException("☹ OOPS!!! The date of a event cannot be empty.");
                    }
                    int atPos = input.indexOf("/at");
                    String description = input.substring(6, atPos);
                    String at = input.substring(atPos + 4);
                    if (isValidDate(at)) {
                        LocalDateTime dateTime = LocalDateTime.parse(at, dateFormat);
                        return new AddCommand(description, "E", dateTime);
                    } else {
                        throw new DukeException("☹ OOPS!!! The date time is not of correct format dd/MM/yyyy HHmm.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
        return null;
    }
}
