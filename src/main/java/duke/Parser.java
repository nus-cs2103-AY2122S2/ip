package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import duke.command.AddCommand;
import duke.command.BingChillingCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.OutputCommand;
import duke.command.PostponeCommand;
import duke.command.UnmarkCommand;
import duke.exception.BingChillingException;
import duke.task.Task;
import duke.ui.MessageUi;

/**
 * Contains method to check validity of user input.
 */
public class Parser {

    /**
     * Checks the validity of the user input. If the user input is valid,
     * returns a Command which is then executed to perform its respective function.
     *
     * @param fullCommand User input.
     * @return Command which is then executed.
     * @throws BingChillingException If input is not valid.
     */
    public static Command parse(String fullCommand) throws BingChillingException {
        MessageUi messageUi = new MessageUi();
        if (fullCommand.isEmpty()) {
            throw new BingChillingException(messageUi.showInvalidCommandMessage());
        }
        String[] splitCommand = fullCommand.split(" ");
        String command = splitCommand[0];

        if (!BingChillingCommand.isDukeCommand(command)) {
            throw new BingChillingException(messageUi.showInvalidCommandMessage());
        }

        HashMap<String, String> commandTypeMap = BingChillingCommand.getTaskTypeMap();
        String commandType = commandTypeMap.get(command);
        switch (commandType) {
        case "ADD_COMMAND":
            parseAddCommand(fullCommand, splitCommand);
            break;
        case "MARK_COMMAND":
            parseMarkCommand(splitCommand);
            break;
        case "UNMARK_COMMAND":
            parseUnmarkCommand(splitCommand);
            break;
        case "OUTPUT_COMMAND":
            parseOutputCommand(splitCommand);
            break;
        case "DELETE_COMMAND":
            parseDeleteCommand(splitCommand);
            break;
        case "POSTPONE_COMMAND":
            parsePostponeCommand(fullCommand, splitCommand);
            break;
        case "EXIT_COMMAND":
            parseExitCommand(splitCommand);
            break;
        case "CLEAR_COMMAND":
            parseClearCommand(splitCommand);
            break;
        case "FIND_COMMAND":
            break;
        default:
            assert false : commandTypeMap;
        }

        return getCommand(fullCommand, commandType);
    }

    /**
     * Parse the input belonging to the Mark Command class.
     *
     * @param splitCommand User input.
     * @throws BingChillingException Command is of incorrect format.
     */
    private static void parseMarkCommand(String[] splitCommand) throws BingChillingException {
        MessageUi messageUi = new MessageUi();
        String invalidFormatMessage = messageUi.showInvalidFormatMessage();
        try {
            if (splitCommand.length > 2) {
                throw new BingChillingException(invalidFormatMessage);
            }
            Integer.parseInt(splitCommand[1]);
        } catch (NumberFormatException error) {
            throw new BingChillingException(invalidFormatMessage);
        }
    }

    /**
     * Parse the input belonging to the Unmark Command class.
     *
     * @param splitCommand User input which is split by the " " regex.
     * @throws BingChillingException Command is of incorrect format.
     */
    private static void parseUnmarkCommand(String[] splitCommand) throws BingChillingException {
        MessageUi messageUi = new MessageUi();
        String invalidFormatMessage = messageUi.showInvalidFormatMessage();
        try {
            if (splitCommand.length > 2) {
                throw new BingChillingException(invalidFormatMessage);
            }
            Integer.parseInt(splitCommand[1]);
        } catch (NumberFormatException error) {
            throw new BingChillingException(invalidFormatMessage);
        }
    }

    /**
     * Parse the input belonging to the Delete Command class.
     *
     * @param splitCommand User input which is split by the " " regex.
     * @throws BingChillingException Command is of incorrect format.
     */
    private static void parseDeleteCommand(String[] splitCommand) throws BingChillingException {
        MessageUi messageUi = new MessageUi();
        String invalidFormatMessage = messageUi.showInvalidFormatMessage();
        try {
            if (splitCommand.length > 2) {
                throw new BingChillingException(invalidFormatMessage);
            }
            Integer.parseInt(splitCommand[1]);
        } catch (NumberFormatException err) {
            throw new BingChillingException(invalidFormatMessage);
        }
    }

    /**
     * Parse the input belonging to the Postpone Command class.
     *
     * @param splitCommand User input which is split by the " " regex.
     * @throws BingChillingException Command is of incorrect format.
     */
    private static void parsePostponeCommand(String input, String[] splitCommand) throws BingChillingException {
        MessageUi messageUi = new MessageUi();
        String invalidFormatMessage = messageUi.showInvalidFormatMessage();
        if (splitCommand.length != 3) {
            throw new BingChillingException(invalidFormatMessage);
        }
        try {
            Integer.parseInt(input.split(" ")[1]);
            String postponeDate = input.split(" ")[2];
            LocalDate.parse(postponeDate, Task.getInputDateFormat());
        } catch (DateTimeParseException err) {
            throw new BingChillingException(invalidFormatMessage);
        } catch (NumberFormatException err) {
            throw new BingChillingException(invalidFormatMessage);
        }
    }

    /**
     * Parse the input belonging to the Add Command class.
     *
     * @param fullCommand  User input.
     * @param splitCommand User input which is split by the " " regex.
     * @throws BingChillingException Command is of incorrect format.
     */
    private static void parseAddCommand(String fullCommand, String[] splitCommand) throws BingChillingException {
        String taskType = splitCommand[0];
        MessageUi messageUi = new MessageUi();
        String invalidFormatMessage = messageUi.showInvalidFormatMessage();
        try {
            switch (taskType) {
            case "todo":
                if (splitCommand.length == 1) {
                    throw new BingChillingException(invalidFormatMessage);
                }
                break;
            case "event":
                boolean hasWrongEventTaskFormat = !(fullCommand.contains(" /at "))
                        || fullCommand.split(" /at ", 2).length == 1
                        || fullCommand.split(" /at ")[0].split("event").length == 0;
                if (hasWrongEventTaskFormat) {
                    throw new BingChillingException(invalidFormatMessage);
                }
                String eventDate = fullCommand.split("event ", 2)[1]
                        .split("/at ")[1];
                LocalDate.parse(eventDate, Task.getInputDateFormat());
                break;
            case "deadline":
                boolean hasWrongDeadlineTaskFormat = !(fullCommand.contains(" /by "))
                        || fullCommand.split(" /by ", 2).length == 1
                        || fullCommand.split(" /by ")[0].split("deadline").length == 0;
                if (hasWrongDeadlineTaskFormat) {
                    throw new BingChillingException(invalidFormatMessage);
                }
                String deadlineDate = fullCommand.split("deadline ", 2)[1].split("/by ")[1];
                LocalDate.parse(deadlineDate, Task.getInputDateFormat());
                break;
            default:
                assert false : taskType;
            }
        } catch (DateTimeParseException err) {
            throw new BingChillingException(invalidFormatMessage);
        }
    }

    /**
     * Parse the input belonging to the Exit Command class.
     *
     * @param splitCommand User input which is split by the " " regex.
     * @throws BingChillingException Command is of incorrect format.
     */
    private static void parseExitCommand(String[] splitCommand) throws BingChillingException {
        MessageUi messageUi = new MessageUi();
        String invalidFormatMessage = messageUi.showInvalidFormatMessage();
        if (splitCommand.length != 1) {
            throw new BingChillingException(invalidFormatMessage);
        }
    }

    /**
     * Parse the input belonging to the Clear Command class.
     *
     * @param splitCommand User input which is split by the " " regex.
     * @throws BingChillingException Command is of incorrect format.
     */
    private static void parseClearCommand(String[] splitCommand) throws BingChillingException {
        MessageUi messageUi = new MessageUi();
        String invalidFormatMessage = messageUi.showInvalidFormatMessage();
        if (splitCommand.length != 1) {
            throw new BingChillingException(invalidFormatMessage);
        }
    }

    /**
     * Parse the input belonging to the Output Command class.
     *
     * @param splitCommand User input which is split by the " " regex.
     * @throws BingChillingException Command is of incorrect format.
     */
    private static void parseOutputCommand(String[] splitCommand) throws BingChillingException {
        MessageUi messageUi = new MessageUi();
        String invalidFormatMessage = messageUi.showInvalidFormatMessage();
        if (splitCommand.length != 1) {
            throw new BingChillingException(invalidFormatMessage);
        }
    }

    /**
     * Executes the command depending on its class.
     *
     * @param fullCommand User input.
     * @param taskType    Class of the command that is belongs to.
     * @return Output of the executed command to the user.
     */
    private static Command getCommand(String fullCommand, String taskType) {
        switch (taskType) {
        case "ADD_COMMAND":
            return new AddCommand(fullCommand);
        case "DELETE_COMMAND":
            return new DeleteCommand(fullCommand);
        case "MARK_COMMAND":
            return new MarkCommand(fullCommand);
        case "UNMARK_COMMAND":
            return new UnmarkCommand(fullCommand);
        case "OUTPUT_COMMAND":
            return new OutputCommand(fullCommand);
        case "EXIT_COMMAND":
            return new ExitCommand();
        case "FIND_COMMAND":
            return new FindCommand(fullCommand);
        case "CLEAR_COMMAND":
            return new ClearCommand();
        case "POSTPONE_COMMAND":
            return new PostponeCommand(fullCommand);
        default:
            assert false : taskType;
            return null;
        }
    }
}



