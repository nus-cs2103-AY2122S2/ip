package echo.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import echo.command.Command;
import echo.command.DeadlineCommand;
import echo.command.DeleteCommand;
import echo.command.EventCommand;
import echo.command.FindCommand;
import echo.command.HelpCommand;
import echo.command.ListCommand;
import echo.command.MarkCommand;
import echo.command.TodoCommand;
import echo.command.UnmarkCommand;
import echo.main.EchoException;
import echo.ui.Ui;

/**
 * Parser encapsulates a parser to deal with making sense of the user command.
 */
public class Parser {

    /**
     * Parse user command and determines action to perform.
     *
     * @param input Input command from user.
     * @return Command to be executed.
     *
     * @throws EchoException If input is invalid.
     */
    public static Command parse(String input) throws EchoException {
        try {
            // Split input based on white spaces.
            String[] splitSpace = input.split(" ");
            String command = splitSpace[0];

            switch (command) {
            case ListCommand.COMMAND:
                return prepareListCommand();
            case TodoCommand.COMMAND:
            case DeadlineCommand.COMMAND:
            case EventCommand.COMMAND:
                // Split input based on /.
                String[] splitSlash = input.split("/");

                // Description of task.
                String desc = splitSlash[0].substring(command.length() + 1).trim();

                String dateTimeString;
                LocalDateTime localDateTime;

                // If second input (description) is not specified, throw EchoException.
                if (splitSpace.length == 1) {
                    throw new EchoException(String.format("The description of a %s cannot be empty.", input));
                }

                if (command.equals(TodoCommand.COMMAND)) {
                    return prepareTodoCommand(desc);
                } else {
                    // "/" not specified in input.
                    if (!input.contains("/")) {
                        throw new EchoException("Please specify the time of the task: \n"
                                + Ui.addPrefix("deadline <description> /by <yyyy-mm-dd> <24hr time> \n")
                                + Ui.addPrefix("event <description> /at <yyyy-mm-dd> <24hr time>"));
                    }

                    try {
                        dateTimeString = splitSlash[1].substring(splitSlash[1].indexOf(" ") + 1);
                        localDateTime = LocalDateTime.parse(dateTimeString,
                                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));

                        if (command.equals(DeadlineCommand.COMMAND)) {
                            return prepareDeadlineCommand(input, desc, localDateTime);
                        } else {
                            return prepareEventCommand(input, desc, localDateTime);
                        }
                    } catch (DateTimeParseException e) {
                        throw new EchoException("Invalid format. Follow the <yyyy-mm-dd> <24hr time> format."
                                + "\n" + Ui.addPrefix("E.g. 2019-10-15 1800"));
                    }
                }
            case MarkCommand.COMMAND:
            case UnmarkCommand.COMMAND:
            case DeleteCommand.COMMAND:
                if (splitSpace.length == 1) {
                    // If second input (task number) is not specified
                    throw new EchoException("Please specify the task number. Eg. mark 1, unmark 1, delete 1");
                }

                // Parse second input into an int, throws NumberFormatException if it's not an int.
                try {
                    int taskIndex = Integer.parseInt(splitSpace[1]) - 1;
                    if (command.equals(MarkCommand.COMMAND)) {
                        return prepareMark(taskIndex);
                    } else if (command.equals(UnmarkCommand.COMMAND)) {
                        return prepareUnmark(taskIndex);
                    } else {
                        return prepareDelete(taskIndex);
                    }
                } catch (NumberFormatException nfe) {
                    // Second input provided by user is not an int.
                    throw new EchoException("Second input must be an integer. Eg. mark 1, unmark 1, delete 1");
                }
            case FindCommand.COMMAND:
                if (splitSpace.length == 1) {
                    // If second input (description) is not specified
                    throw new EchoException("Please specify the description to find!");
                }
                return prepareFind(input.substring(command.length() + 1).trim());
            default:
                return prepareHelpCommand();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Deals with input that is just white space.
            throw new EchoException("Invalid input!!!");
        }
    }

    /**
     * Prepare list command.
     *
     * @return ListCommand.
     */
    private static Command prepareListCommand() {
        return new ListCommand();
    }

    /**
     * Prepare list command.
     *
     * @param desc Description of todo command.
     * @return TodoCommand.
     */
    private static Command prepareTodoCommand(String desc) {
        return new TodoCommand(desc);
    }

    /**
     * Prepare deadline command.
     *
     * @param input Input from user.
     * @param desc Description of deadline command.
     * @param localDateTime Local date and time.
     *
     * @return DeadlineCommand.
     *
     * @throws EchoException If input is invalid.
     */
    private static Command prepareDeadlineCommand(String input, String desc, LocalDateTime localDateTime)
            throws EchoException {
        if (!input.contains("/by")) {
            // If /by is not specified.
            throw new EchoException("Invalid input. Make sure to include '/by'\n"
                    + "    E.g. deadline return book /by 2019-10-15 1800");
        }
        return new DeadlineCommand(desc, localDateTime);
    }

    /**
     * Prepare event command.
     *
     * @param input Input from user.
     * @param desc Description of event command.
     * @param localDateTime Local date and time.
     *
     * @return EventCommand.
     *
     * @throws EchoException If input is invalid.
     */
    private static Command prepareEventCommand(String input, String desc, LocalDateTime localDateTime)
            throws EchoException {
        if (!input.contains("/at")) {
            // If /at is not specified.
            throw new EchoException("Invalid input. Make sure to include '/at' \n."
                    + "    E.g. event meeting /at 2019-10-15 1800");
        }
        return new EventCommand(desc, localDateTime);
    }

    /**
     * Prepare mark command.
     *
     * @param i Index of task.
     *
     * @return MarkCommand.
     */
    private static Command prepareMark(int i) {
        return new MarkCommand(i);
    }

    /**
     * Prepare unmark command.
     *
     * @param i Index of task.
     *
     * @return UnmarkCommand.
     */
    private static Command prepareUnmark(int i) {
        return new UnmarkCommand(i);
    }

    /**
     * Prepare delete command.
     *
     * @param i Index of task.
     *
     * @return DeleteCommand.
     */
    private static Command prepareDelete(int i) {
        return new DeleteCommand(i);
    }

    /**
     * Prepare find command.
     *
     * @param desc Description to find.
     * @return FindCommand.
     */
    private static Command prepareFind(String desc) {
        return new FindCommand(desc);
    }

    /**
     * Prepare help command.
     *
     * @return HelpCommand.
     */
    private static Command prepareHelpCommand() {
        return new HelpCommand();
    }
}
