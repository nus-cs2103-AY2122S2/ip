package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.EditCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyArgumentException;
import duke.exceptions.ExcessArgumentException;
import duke.exceptions.InvalidArgumentException;
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
    private Command prepareTodo(String request) throws EmptyArgumentException {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("todo") : "request is a todo";

        String[] parsedReq = request
                .strip()
                .split(" ");

        if (parsedReq.length == 1) {
            throw new EmptyArgumentException("description of the todo", "add");
        }

        assert parsedReq.length != 1 : "length of parsed request should be longer than one";

        return new AddCommand(
                new Todo(request
                        .substring(5)
                        .strip()));
    }

    /**
     * Parses input in the context of adding a deadline.
     *
     * @param request full user input
     * @return the prepared add command
     */
    private Command prepareDeadline(String request) throws EmptyArgumentException, InvalidArgumentException {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("deadline") : "request is a deadline";

        if (request.strip()
                .length() == 8) {
            throw new EmptyArgumentException("description of the deadline", "add");
        } else if (!request.contains(" /by ")) {
            throw new EmptyArgumentException("date/time of the deadline", "add");
        }

        String next = request.substring(8);
        String[] parsedReq = next.split("/by");
        String desc = parsedReq[0].strip();
        String by = parsedReq[1].strip();

        if (desc.length() == 0) {
            throw new EmptyArgumentException("description of the deadline", "add");
        } else if (by.length() == 0) {
            throw new EmptyArgumentException("date/time of the deadline", "add");
        }

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
            throw new InvalidArgumentException("date and/or time in one of the following formats:\n"
                    + "    yyyy-MM-dd HHmm\n"
                    + "    yyyy-MM-dd\n"
                    + "    or HHmm");
        }
    }


    /**
     * Parses input in the context of adding an event.
     *
     * @param request full user input
     * @return the prepared add command
     */
    private Command prepareEvent(String request) throws EmptyArgumentException, InvalidArgumentException {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("event") : "request is an event";

        if (request.strip()
                .length() == 5) {
            throw new EmptyArgumentException("description of the event", "add");
        } else if (!request.contains(" /at ")) {
            throw new EmptyArgumentException("date/time of the event", "add");
        }

        String next = request.substring(5);
        String[] parsedReq = next.split("/at");
        String desc = parsedReq[0].strip();
        String at = parsedReq[1].strip();

        if (desc.length() == 0) {
            throw new EmptyArgumentException("description of the event", "add");
        } else if (at.length() == 0) {
            throw new EmptyArgumentException("date/time of the event", "add");
        }
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
            throw new InvalidArgumentException("date and/or time in one of the following formats:\n"
                    + "    yyyy-MM-dd HHmm\n"
                    + "    yyyy-MM-dd\n"
                    + "    or HHmm");
        }
    }

    /**
     * Parses input in the context of a delete command.
     *
     * @param request full user input
     * @return the prepared delete command
     */
    private Command prepareDelete(String request) throws EmptyArgumentException, InvalidArgumentException {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("delete") : "request is a delete";

        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            throw new EmptyArgumentException("task number of the task", "delete");
        }

        assert parsedReq.length == 2 : "delete command should have an argument";
        try {
            return new DeleteCommand(
                    Integer.parseInt(parsedReq[1]));
        } catch (NumberFormatException n) {
            throw new InvalidArgumentException("valid task number to delete");
        }
    }

    /**
     * Parses input in the context of a mark command.
     *
     * @param request full user input
     * @return the prepared mark command
     */
    private Command prepareMark(String request) throws InvalidArgumentException, EmptyArgumentException {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("mark") : "request is a mark";

        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            throw new EmptyArgumentException("task number of the task", "mark as done");
        } else {
            assert parsedReq.length == 2 : "mark command should have an argument";

            try {
                return new MarkCommand(
                        Integer.parseInt(parsedReq[1]));
            } catch (NumberFormatException n) {
                throw new InvalidArgumentException("valid task to mark as done");
            }
        }
    }

    /**
     * Parses input in the context of an unmark command.
     *
     * @param request full user input
     * @return the prepared unmark command
     */
    private Command prepareUnmark(String request) throws EmptyArgumentException, InvalidArgumentException {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("unmark") : "request is an unmark";

        String[] parsedReq = request.split(" ");

        if (parsedReq.length != 2) {
            throw new EmptyArgumentException("task number of the task", "mark as undone");
        } else {
            assert parsedReq.length == 2 : "unmark command should have an argument";

            try {
                return new UnmarkCommand(
                        Integer.parseInt(parsedReq[1]));
            } catch (NumberFormatException n) {
                throw new InvalidArgumentException("valid task number to make as undone");
            }
        }
    }

    /**
     * Parses input in the context of a find command.
     *
     * @param request full user input
     * @return the prepared find command
     */
    private Command prepareFind(String request) throws EmptyArgumentException {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("find") : "request is a find";

        if (request.strip().equals("find")) {
            throw new EmptyArgumentException("keyword(s) for the task(s)", "find");
        }

        String parsedReq = request.substring(6);
        return new FindCommand(parsedReq);
    }

    private Command prepareEdit(String request) throws EmptyArgumentException, InvalidArgumentException,
            ExcessArgumentException {
        assert !request.equals("") : "request should not be empty";
        assert request.contains("update") : "request is a update";

        if (request.strip().equals("edit")) {
            throw new EmptyArgumentException("task number of the task", "update");
        }

        String next = request.substring(4).strip();
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(String.valueOf(next.charAt(0)));
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("task to be edited");
        }

        String edits = next.substring(1).strip();
        if (edits.length() == 0) {
            throw new EmptyArgumentException("edits", "edit the tasks with");
        }

        if (edits.startsWith("desc/")) {
            String description = edits.substring(5);
            if (description.contains("desc/") || description.contains("dt/")) {
                throw new ExcessArgumentException("a single edit");
            } else if (description.length() == 0) {
                throw new EmptyArgumentException("description", "update the task with");
            }
            return new EditCommand(taskIndex, description);
        } else if (edits.startsWith("dt/")) {
            String dateTime = edits.substring(3).strip();
            if (dateTime.contains("desc/") || dateTime.contains("dt/")) {
                throw new ExcessArgumentException("a single edit");
            } else if (dateTime.length() == 0) {
                throw new EmptyArgumentException("date and/or time", "update the task with");
            }

            Format f = Parser.parseDateTime(dateTime);

            switch (f) {
            case DATETIME:
                String[] dateTimeArray = dateTime.split(" ");
                String date = dateTimeArray[0];
                String time = dateTimeArray[1];
                return new EditCommand(taskIndex,
                        LocalDate.parse(date, DATE_IN),
                        LocalTime.parse(time, TIME_IN));
            case DATE:
                return new EditCommand(taskIndex,
                        LocalDate.parse(dateTime, DATE_IN));
            case TIME:
                return new EditCommand(taskIndex,
                        LocalTime.parse(dateTime, TIME_IN));
            case INVALID:
            default:
                throw new InvalidArgumentException("date and/or time in one of the following formats:\n"
                        + "    yyyy-MM-dd HHmm\n"
                        + "    yyyy-MM-dd\n"
                        + "    or HHmm");
            }
        }

        throw new InvalidArgumentException("edit with prefix desc/ for descriptions and dt/ for date and/or time");
    }

    /**
     * Parses input into a command for execution.
     *
     * @param input the user's initial input
     * @return the command based on the user's input
     */
    public Command parseCommand(String input) throws DukeException {
        if (input.isBlank()) {
            throw new EmptyArgumentException("command", "execute");
        }

        String[] array = input.strip().split(" ");
        String commandPrefix = array[0].toUpperCase();

        switch (commandPrefix) {
        case "BYE":
            return new ExitCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return prepareMark(input);
        case "UNMARK":
            return prepareUnmark(input);
        case "TODO":
            return prepareTodo(input);
        case "DEADLINE":
            return prepareDeadline(input);
        case "EVENT":
            return prepareEvent(input);
        case "DELETE":
            return prepareDelete(input);
        case "FIND":
            return prepareFind(input);
        case "EDIT":
            return prepareEdit(input);
        default:
            throw new DukeException("My apologies, but it seems that I do not understand your request.");
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
