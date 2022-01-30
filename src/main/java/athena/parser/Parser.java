package athena.parser;

import athena.commands.Command;
import athena.commands.DeadlineCommand;
import athena.commands.DeleteCommand;
import athena.commands.EventCommand;
import athena.commands.ListCommand;
import athena.commands.MarkCommand;
import athena.commands.ShutdownCommand;
import athena.commands.TodoCommand;
import athena.commands.UnmarkCommand;
import athena.exceptions.InputErrorCode;
import athena.exceptions.InputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("d/M/yyyy Hmm");

    public static Command getCommand(String input) throws InputException {
        // Separate out the command keyword from the other arguments given
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String arguments = "";
        if (splitInput.length > 1) {
            arguments = splitInput[1];
        }
        // Generate command object to return
        switch (command) {
        case "todo":
            return new TodoCommand(getTaskNameFromInput(arguments));
            // No fallthrough necessary
        case "deadline":
            return new DeadlineCommand(getTaskNameFromInput(arguments),
                    getDateTimeFromInput(arguments, "/by"));
            // No fallthrough necessary
        case "event":
            return new EventCommand(getTaskNameFromInput(arguments),
                    getDateTimeFromInput(arguments, "/at"));
            // No fallthrough necessary
        case "mark":
            return new MarkCommand(getTaskNumberFromInput(arguments));
            // No fallthrough necessary
        case "unmark":
            return new UnmarkCommand(getTaskNumberFromInput(arguments));
            // No fallthrough necessary
        case "delete":
            return new DeleteCommand(getTaskNumberFromInput(arguments));
            // No fallthrough necessary
        case "list":
            return new ListCommand();
            // No fallthrough necessary
        case "bye":
            return new ShutdownCommand();
            // No fallthrough necessary
        default:
            throw new InputException(InputErrorCode.INVALID_COMMAND);
            // No fallthrough necessary
        }
    }

    private static int getTaskNumberFromInput(String input) throws InputException {
        int taskNumber = -1;
        try {
            taskNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputException(InputErrorCode.MISSING_TASK_NUMBER);
        }
        return taskNumber;
    }

    private static String getTaskNameFromInput(String input) throws InputException {
        // Remove excess input arising from extra fields like /by and /at
        input = input.split("/")[0].strip();
        if (input.isEmpty()) {
            throw new InputException(InputErrorCode.MISSING_TASK_NAME);
        }
        return input;
    }

    private static LocalDateTime getDateTimeFromInput(String input, String keyword)
            throws InputException {
        LocalDateTime dateTime;
        if (!input.contains(keyword)) {
            throw new InputException(InputErrorCode.MISSING_TASK_DATETIME);
        } else {
            String dateTimeString = input.split(keyword, 2)[1].strip();
            if (dateTimeString.isEmpty()) {
                throw new InputException(InputErrorCode.MISSING_TASK_DATETIME);
            } else {
                try {
                    dateTime = LocalDateTime.parse(dateTimeString, inputFormatter);
                } catch (DateTimeParseException e) {
                    throw new InputException(InputErrorCode.INVALID_TASK_DATETIME);
                }
            }
        }
        return dateTime;
    }
}
