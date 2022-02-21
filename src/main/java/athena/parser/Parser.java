package athena.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import athena.commands.Command;
import athena.commands.DeadlineCommand;
import athena.commands.DeleteCommand;
import athena.commands.EventCommand;
import athena.commands.FindCommand;
import athena.commands.ListCommand;
import athena.commands.MarkCommand;
import athena.commands.ReminderCommand;
import athena.commands.ShutdownCommand;
import athena.commands.TodoCommand;
import athena.commands.UnmarkCommand;
import athena.exceptions.InputErrorCode;
import athena.exceptions.InputException;

/**
 * Encapsulates helper methods to parse user input given to Athena.
 */
public class Parser {
    public static final int DAYS_IN_WEEK = 7;
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yyyy Hmm");
    private static final String DEADLINE_DATE_KEYWORD = "/by";
    private static final String EVENT_DATE_KEYWORD = "/at";
    private static final String TODAY = "today";
    private static final String TOMORROW = "tomorrow";
    private static final String THIS_WEEK = "this week";
    private static final String NEXT_WEEK = "next week";

    /**
     * Returns a Command object based on the given input, which encapsulates the user
     * command in an object that can later be executed.
     *
     * @param input Raw user input given to Athena.
     * @return Command object encapsulating the user command.
     * @throws InputException If user input is invalid, such as when providing a non-existent command.
     */
    public static Command getCommand(String input) throws InputException {
        String commandWord = getCommandWord(input);
        String commandArgs = getCommandArguments(input);

        // Generate command object to return
        switch (commandWord) {
        case "todo":
            return new TodoCommand(getTaskNameFromArgs(commandArgs));
            // No fallthrough necessary
        case "deadline":
            return new DeadlineCommand(getTaskNameFromArgs(commandArgs),
                    getDateTimeFromArgs(commandArgs, DEADLINE_DATE_KEYWORD));
            // No fallthrough necessary
        case "event":
            return new EventCommand(getTaskNameFromArgs(commandArgs),
                    getDateTimeFromArgs(commandArgs, EVENT_DATE_KEYWORD));
            // No fallthrough necessary
        case "mark":
            return new MarkCommand(getTaskNumberFromArgs(commandArgs));
            // No fallthrough necessary
        case "unmark":
            return new UnmarkCommand(getTaskNumberFromArgs(commandArgs));
            // No fallthrough necessary
        case "delete":
            return new DeleteCommand(getTaskNumberFromArgs(commandArgs));
            // No fallthrough necessary
        case "find":
            return new FindCommand(getSearchPhraseFromArgs(commandArgs));
        case "list":
            return new ListCommand();
            // No fallthrough necessary
        case "remind":
            return new ReminderCommand(getStartDateFromArgs(commandArgs),
                    getEndDateFromArgs(commandArgs));
            // No fallthrough necessary
        case "bye":
            return new ShutdownCommand();
            // No fallthrough necessary
        default:
            throw new InputException(InputErrorCode.INVALID_COMMAND);
            // No fallthrough necessary
        }
    }

    private static String getCommandWord(String input) {
        String[] splitInput = input.split(" ", 2);
        return splitInput[0];
    }

    private static String getCommandArguments(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length >= 2) {
            return splitInput[1];
        } else {
            return "";
        }
    }

    private static int getTaskNumberFromArgs(String args) throws InputException {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InputException(InputErrorCode.MISSING_TASK_NUMBER);
        }
    }

    private static String getTaskNameFromArgs(String args) throws InputException {
        // Strip excess arising from extra arguments like /by and /at
        String stripped = args.split("/")[0].strip();
        if (stripped.isEmpty()) {
            throw new InputException(InputErrorCode.MISSING_TASK_NAME);
        }
        return stripped;
    }

    private static String getSearchPhraseFromArgs(String args) throws InputException {
        if (args.isEmpty()) {
            throw new InputException(InputErrorCode.MISSING_SEARCH_PHRASE);
        }
        return args;
    }

    private static LocalDateTime getDateTimeFromArgs(String args, String keyword)
            throws InputException {
        if (!args.contains(keyword)) {
            throw new InputException(InputErrorCode.MISSING_TASK_DATETIME);
        }

        // Split by keyword, take the latter part.
        String dateTimeString = args.split(keyword, 2)[1].strip();
        if (dateTimeString.isEmpty()) {
            throw new InputException(InputErrorCode.MISSING_TASK_DATETIME);
        }

        try {
            return LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InputException(InputErrorCode.INVALID_TASK_DATETIME);
        }
    }

    private static LocalDate getStartDateFromArgs(String args) throws InputException {
        switch (args.strip()) {
        case TODAY:
        case THIS_WEEK:
            return LocalDate.now();
        // No fallthrough needed
        case TOMORROW:
            return LocalDate.now().plusDays(1);
        // No fallthrough needed
        case NEXT_WEEK:
            return getStartingDateOfNextWeek();
        // No fallthrough needed
        default:
            throw new InputException(InputErrorCode.INVALID_REMINDER_PHRASE);
            // No fallthrough needed
        }
    }

    private static LocalDate getEndDateFromArgs (String args) throws InputException {
        switch (args.strip()) {
        case TODAY:
            return LocalDate.now();
            // No fallthrough needed
        case TOMORROW:
            return LocalDate.now().plusDays(1);
            // No fallthrough needed
        case THIS_WEEK:
            return getDateOfEndOfWeek();
            // No fallthrough needed
        case NEXT_WEEK:
            return getDateOfEndOfWeek().plusDays(DAYS_IN_WEEK);
            // No fallthrough needed
        default:
            throw new InputException(InputErrorCode.INVALID_REMINDER_PHRASE);
            // No fallthrough needed
        }
    }

    private static int getNumDaysToEndOfWeek(LocalDate date) {
        return DAYS_IN_WEEK - date.getDayOfWeek().getValue();
    }

    private static LocalDate getDateOfEndOfWeek() {
        LocalDate currDate = LocalDate.now();
        return currDate.plusDays(getNumDaysToEndOfWeek(currDate));
    }

    private static LocalDate getStartingDateOfNextWeek() {
        LocalDate currDate = LocalDate.now();
        return currDate.plusDays(getNumDaysToEndOfWeek(currDate) + 1);
    }
}
