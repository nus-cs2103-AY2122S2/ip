import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Reads input string and determines action to perform.
     *
     * @param input Input string from user.
     * @throws EchoException If input is missing details or badly formatted.
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
                        localDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));

                        if (command.equals(DeadlineCommand.COMMAND)) {
                            return prepareDeadlineCommand(input, desc, localDateTime);
                        } else {
                            return prepareEventCommand(input, desc, localDateTime);
                        }
                    } catch (DateTimeParseException e) {
                        throw new EchoException("Invalid format for date and time. Follow the <yyyy-mm-dd> <24hr time> format."
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
            default:
                return prepareHelpCommand();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Deals with input that is just white space.
            throw new EchoException("Invalid input!!!");
        }
    }

    private static Command prepareListCommand() {
        return new ListCommand();
    }

    private static Command prepareTodoCommand(String desc) {
        return new TodoCommand(desc);
    }

    private static Command prepareDeadlineCommand(String input, String desc, LocalDateTime localDateTime)
            throws EchoException {
        if (!input.contains("/by")) {
            // If /by is not specified.
            throw new EchoException("Invalid input. Make sure to include '/by'\n"
                    + "    E.g. deadline return book /by 2019-10-15 1800");
        }
        return new DeadlineCommand(desc, localDateTime);
    }

    private static Command prepareEventCommand(String input, String desc, LocalDateTime localDateTime)
            throws EchoException {
        if (!input.contains("/at")) {
            // If /at is not specified.
            throw new EchoException("Invalid input. Make sure to include '/at' \n."
                    + "    E.g. event meeting /at 2019-10-15 1800");
        }
        return new EventCommand(desc, localDateTime);
    }

    private static Command prepareMark(int i) {
        return new MarkCommand(i);
    }

    private static Command prepareUnmark(int i) {
        return new UnmarkCommand(i);
    }

    private static Command prepareDelete(int i) {
        return new DeleteCommand(i);
    }

    private static Command prepareHelpCommand() {
        return new HelpCommand();
    }
}
