package duke.parser;

import duke.DukeException;
import duke.command.*;
import duke.common.Messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    private static final String DEFAULT_DATE_FORMAT = "d/MM/yyyy HHmm";

    public static Command parse(String userInput) throws DukeException {
        String[] userInputArr = userInput.split(" ", 2);
        switch (userInputArr[0]) {
            case "mark":
                return new MarkTaskCommand(Integer.parseInt(userInputArr[1]) - 1, true);
            case "unmark":
                return new MarkTaskCommand(Integer.parseInt(userInputArr[1]) - 1, false);
            case "delete":
                return new DeleteCommand(Integer.parseInt(userInputArr[1]) - 1);
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "todo":
                return prepareAddTodoCommand(userInputArr[1]);
            case "event":
                userInput = String.join(" ",
                        Arrays.copyOfRange(userInputArr, 1, userInputArr.length));
                return prepareAddEventCommand(userInput);
            case "deadline":
                userInput = String.join(" ",
                        Arrays.copyOfRange(userInputArr, 1, userInputArr.length));
                return prepareAddDeadlineCommand(userInput);
            default:
                throw new DukeException(Messages.MESSAGE_ERROR_INVALID_COMMAND);
        }
    }

    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
            return LocalDateTime.parse(dateTime, format);
        } catch (DateTimeParseException e) {
            throw new DukeException(Messages.MESSAGE_ERROR_INVALID_DATETIME_FORMAT);
        }
    }

    public static String parseTaskSize(int size) {
        return size > 1 ? "s" : "";
    }

    private static Command prepareAddTodoCommand(String title) throws DukeException {
        if (title.isEmpty()) {
            throw new DukeException(Messages.MESSAGE_ERROR_EMPTY_TITLE);
        }
        return new AddTodoCommand(title);
    }

    private static Command prepareAddEventCommand(String userInput) throws DukeException {
        String[] taskArr = userInput.split(" /at ");
        if (!validateCommandInput(taskArr)) {
            throw new DukeException(Messages.MESSAGE_ERROR_INVALID_COMMAND);
        }
        String title = taskArr[0];
        String eventAt = taskArr[1];

        return new AddEventCommand(title, parseDateTime(eventAt));
    }

    private static Command prepareAddDeadlineCommand(String userInput) throws DukeException {
        String[] taskArr = userInput.split(" /by ");
        if (!validateCommandInput(taskArr)) {
            throw new DukeException(Messages.MESSAGE_ERROR_INVALID_COMMAND);
        }
        String title = taskArr[0];
        String dueBy = taskArr[1];

        return new AddDeadlineCommand(title, parseDateTime(dueBy));
    }

    private static boolean validateCommandInput(String[] inputWithDate) {
        return inputWithDate.length == 2 && !inputWithDate[0].isEmpty() && !inputWithDate[1].isEmpty();
    }

}
