package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parses user input.
 */
public class Parser {
    // Input formats of date and times
    private static final DateTimeFormatter DATE_TIME_IN = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_IN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_IN = DateTimeFormatter.ofPattern("HHmm");

    // Output formats of date and times
    private static final DateTimeFormatter DATE_TIME_OUT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private static final DateTimeFormatter DATE_OUT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIME_OUT = DateTimeFormatter.ofPattern("hh:mm a");

    private enum Format {
        DATE,
        TIME,
        DATETIME,
        INVALID
    }

    /**
     * Checks if a given date and time is in valid format.
     *
     * @param dt a string containing a date and time
     * @return true if the string provided is a valid date and time, false otherwise
     */
    private static boolean isValidDateTime(String dt) {
        try {
            LocalDateTime.parse(dt, DATE_TIME_IN);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a given date is in valid format.
     *
     * @param d a string containing a date
     * @return true if the string provided is a valid date, false otherwise
     */
    private static boolean isValidDate(String d) {
        try {
            LocalDate.parse(d, DATE_IN);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a given time is in valid format.
     *
     * @param t a string containing a time
     * @return true if the string provided is a valid time, false otherwise
     */
    private static boolean isValidTime(String t) {
        try {
            LocalTime.parse(t, TIME_IN);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Parses input in the context of adding a todo.
     *
     * @param request full user input
     * @return the prepared command
     */
    private Command prepareTodo(String request) {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("todo") : "request is a todo";

        String[] parsedReq = request
                .strip()
                .split(" ");

        if (parsedReq.length == 1) {
            return new IncorrectCommand("The description of a todo cannot be empty.");
        } else {
            assert parsedReq.length != 1 : "length of parsed request should be longer than one";

            return new AddCommand(
                    new Todo(request
                            .substring(5)
                            .strip()));
        }
    }

    /**
     * Parses input in the context of adding a deadline.
     *
     * @param request full user input
     * @return the prepared add command
     */
    private Command prepareDeadline(String request) {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("deadline") : "request is a deadline";

        if (request.strip()
                .length() == 8) {
            return new IncorrectCommand("The description of a deadline cannot be empty.");
        } else if (!request.contains(" /by ")) {
            return new IncorrectCommand("You left the date/time of the deadline empty!");
        }

        String next = request.substring(8);
        String[] parsedReq = next.split("/by");
        String desc = parsedReq[0].strip();
        String by = parsedReq[1].strip();

        if (desc.length() == 0) {
            return new IncorrectCommand("The description of a deadline cannot be empty.");
        } else if (by.length() == 0) {
            return new IncorrectCommand("You left the date/time of the deadline empty!");
        } else {
            assert desc.length() > 0 && by.length() > 0 : "description and by should exist";

            Parser.Format f = Parser.parseDateTime(by);

            switch (f) {
            case DATETIME:
                String[] s = by.split(" ");
                return new AddCommand(
                        new Deadline(desc,
                                LocalDate.parse(s[0], DATE_IN),
                                LocalTime.parse(s[1], TIME_IN)));
            case DATE:
                return new AddCommand(
                        new Deadline(desc,
                                LocalDate.parse(by, DATE_IN)));
            case TIME:
                return new AddCommand(
                        new Deadline(desc,
                                LocalTime.parse(by, TIME_IN)));
            case INVALID:
            default:
                return new IncorrectCommand("Please enter the date and/or time in the specified format:\n"
                        + "    yyyy-MM-dd HHmm\n"
                        + "    yyyy-MM-dd\n"
                        + "    or HHmm");
            }
        }
    }

    /**
     * Parses input in the context of adding an event.
     *
     * @param request full user input
     * @return the prepared add command
     */
    private Command prepareEvent(String request) {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("event") : "request is an event";

        if (request.strip()
                .length() == 5) {
            return new IncorrectCommand("The description of an event cannot be empty.");
        } else if (!request.contains(" /at ")) {
            return new IncorrectCommand("You left the date/time of the event empty!");
        }

        String next = request.substring(5);
        String[] parsedReq = next.split("/at");
        String desc = parsedReq[0].strip();
        String at = parsedReq[1].strip();

        if (desc.length() == 0) {
            return new IncorrectCommand("The description of an event cannot be empty.");
        } else if (at.length() == 0) {
            return new IncorrectCommand("You left the date/time of the event empty!");
        } else {
            assert desc.length() > 0 && at.length() > 0 : "description and at should exist";

            Parser.Format f = Parser.parseDateTime(at);

            switch (f) {
            case DATETIME:
                String[] s = at.split(" ");
                return new AddCommand(
                        new Event(desc,
                                LocalDate.parse(s[0], DATE_IN),
                                LocalTime.parse(s[1], TIME_IN)));
            case DATE:
                return new AddCommand(
                        new Event(desc,
                                LocalDate.parse(at, DATE_IN)));
            case TIME:
                return new AddCommand(
                        new Event(desc,
                                LocalTime.parse(at, TIME_IN)));
            case INVALID:
            default:
                return new IncorrectCommand("Please enter the date and/or time in the specified format:\n"
                        + "    yyyy-MM-dd HHmm\n"
                        + "    yyyy-MM-dd\n"
                        + "    or HHmm");
            }
        }
    }

    /**
     * Parses input in the context of a delete command.
     *
     * @param request full user input
     * @return the prepared delete command
     */
    private Command prepareDelete(String request) {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("delete") : "request is a delete";

        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            return new IncorrectCommand("Please tell me which task you would like to delete.");
        } else {
            assert parsedReq.length == 2 : "delete command should have an argument";
            try {
                return new DeleteCommand(
                        Integer.parseInt(parsedReq[1]));
            } catch (NumberFormatException n) {
                return new IncorrectCommand("Please enter a valid task number to delete!");
            }
        }
    }

    /**
     * Parses input in the context of a mark command.
     *
     * @param request full user input
     * @return the prepared mark command
     */
    private Command prepareMark(String request) {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("mark") : "request is a mark";

        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            return new IncorrectCommand("Please tell me which task you would like to be marked as done.");
        } else {
            assert parsedReq.length == 2 : "mark command should have an argument";
            try {
                return new MarkCommand(
                        Integer.parseInt(parsedReq[1]));
            } catch (NumberFormatException n) {
                return new IncorrectCommand("Please enter a valid task to mark as done!");
            }
        }
    }

    /**
     * Parses input in the context of an unmark command.
     *
     * @param request full user input
     * @return the prepared unmark command
     */
    private Command prepareUnmark(String request) {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("unmark") : "request is an unmark";

        String[] parsedReq = request.split(" ");

        if (parsedReq.length != 2) {
            return new IncorrectCommand("Please tell me which task you would like to be marked as undone.");
        } else {
            assert parsedReq.length == 2 : "unmark command should have an argument";
            try {
                return new UnmarkCommand(
                        Integer.parseInt(parsedReq[1]));
            } catch (NumberFormatException n) {
                return new IncorrectCommand("Please enter a valid task number to mark as undone!");
            }
        }
    }

    /**
     * Parses input in the context of a find command.
     *
     * @param request full user input
     * @return the prepared find command
     */
    private Command prepareFind(String request) {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("find") : "request is a find";

        if (request.strip().equals("find")) {
            return new IncorrectCommand("Please provide me with keywords for the task(s) you would like to find.");
        }

        String parsedReq = request.substring(6);
        return new FindCommand(parsedReq);
    }

    /**
     * Parses input into a command for execution.
     *
     * @param input the user's initial input
     * @return the command based on the user's input
     */
    public Command parseCommand(String input) {
        if (input.strip().equals("bye")) {
            return new ExitCommand();
        } else if (input.strip().equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return prepareMark(input);
        } else if (input.startsWith("unmark")) {
            return prepareUnmark(input);
        } else if (input.startsWith("todo")) {
            return prepareTodo(input);
        } else if (input.startsWith("deadline")) {
            return prepareDeadline(input);
        } else if (input.startsWith("event")) {
            return prepareEvent(input);
        } else if (input.startsWith("delete")) {
            return prepareDelete(input);
        } else if (input.startsWith("find")) {
            return prepareFind(input);
        } else {
            return new IncorrectCommand("My apologies, but it seems that I do not understand your request.");
        }
    }

    /**
     * Checks if a string is in a valid date/time format and returns its format.
     *
     * @param input the date/time string to be checked
     * @return the format of the date/time specified
     */
    private static Format parseDateTime(String input) {
        if (isValidDateTime(input)) {
            return Format.DATETIME;
        } else if (isValidTime(input)) {
            return Format.TIME;
        } else if (isValidDate(input)) {
            return Format.DATE;
        } else {
            return Format.INVALID;
        }
    }
}
