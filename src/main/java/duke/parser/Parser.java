package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PrintCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.ErrorMessage;

/**
 * A class that handles the logic of parsing the user command.
 */
public class Parser {
    static final String DATETIME_COMMAND_FOR_DEADLINE = "by";
    static final String DATETIME_COMMAND_FOR_EVENT = "at";
    static final String DATE_COMMAND_FOR_PRINT = "/on";

    /**
     * Parses the command line input, validates the command type and
     * initialises the command.
     *
     * @param commandLine The command line input from the user
     * @return The Command object that is initialised
     * @throws DukeException If the command is invalid or if there
     * are any errors when initialising the command
     */
    public Command parse(String commandLine) throws DukeException {
        String[] commandLineParts = commandLine.split("\\s+", 2);

        CommandType commandType = validateCommandType(commandLineParts[0].toLowerCase());

        if (commandType == null) {
            throw new DukeException(ErrorMessage.ERROR_INVALID_COMMAND.toString());
        }

        String commandInfo = (commandLineParts.length == 2) ? commandLineParts[1] : "";

        boolean isCommandTypeBye = commandType == CommandType.BYE;
        boolean isCommandTypeList = commandType == CommandType.LIST;
        boolean isCommandInfoEmpty = commandInfo.isEmpty();

        // If user inputs extra information for "bye" or "list" commands,
        // then it is not a valid command
        if ((isCommandTypeBye || isCommandTypeList) && !isCommandInfoEmpty) {
            throw new DukeException(ErrorMessage.ERROR_INVALID_COMMAND.toString());
        }

        return initialiseCommand(commandType, commandInfo);
    }

    /**
     * Validates the command type to check if it is a valid command.
     *
     * @param commandType Command type in String
     * @return The command type in CommandType object if it is a
     * valid command, null otherwise
     */
    private CommandType validateCommandType(String commandType) {
        for (CommandType c : CommandType.values()) {
            if (c.getLabel().equals(commandType)) {
                return c;
            }
        }

        return null;
    }

    /**
     * Initialise the command.
     *
     * @param commandType Command type
     * @param commandInfo Command information
     * @return The Command object that is initialised
     * @throws DukeException If the command is invalid or if there
     * are any errors when initialising the command
     */
    private Command initialiseCommand(CommandType commandType, String commandInfo) throws DukeException {
        Command command;
        int taskNum;
        String taskDescription;
        String taskInfo;
        String date;
        String keyword;

        switch (commandType) {
        case BYE:
            command = new ExitCommand();
            break;
        case LIST:
            command = new ListCommand();
            break;
        case MARK:
            taskNum = getTaskNumFromMarkCommand(commandInfo);
            command = new MarkCommand(taskNum);
            break;
        case UNMARK:
            taskNum = getTaskNumFromUnmarkCommand(commandInfo);
            command = new UnmarkCommand(taskNum);
            break;
        case DELETE:
            taskNum = getTaskNumFromDeleteCommand(commandInfo);
            command = new DeleteCommand(taskNum);
            break;
        case TODO:
            taskDescription = getTaskDescriptionFromToDoCommand(commandInfo);
            command = new AddToDoCommand(taskDescription);
            break;
        case DEADLINE:
            taskInfo = getTaskInfoFromDeadlineCommand(commandInfo);
            command = new AddDeadlineCommand(taskInfo);
            break;
        case EVENT:
            taskInfo = getTaskInfoFromEventCommand(commandInfo);
            command = new AddEventCommand(taskInfo);
            break;
        case PRINT:
            date = getDateFromPrintCommand(commandInfo);
            command = new PrintCommand(date);
            break;
        case FIND:
            keyword = getKeywordFromFindCommand(commandInfo);
            command = new FindCommand(keyword);
            break;
        default:
            // Error detection for any invalid commands has already been
            // handled before this initialiseCommand method. Hence, the
            // default case should not be reached, but serves as an
            // additional layer of check.
            throw new DukeException(ErrorMessage.ERROR_INVALID_COMMAND.toString());
        }

        return command;
    }

    /**
     * Gets the task number from Mark command.
     *
     * @param commandInfo Command information
     * @return The task number
     * @throws DukeException If the task number is empty or not a valid integer
     */
    private int getTaskNumFromMarkCommand(String commandInfo) throws DukeException {
        String errorMessageTaskNumEmpty = ErrorMessage.ERROR_MARK_TASK_NUM_EMPTY.toString();
        String errorMessageTaskNumInvalid = ErrorMessage.ERROR_MARK_TASK_NUM_INVALID.toString();
        return getTaskNumFromCommandInfo(commandInfo, errorMessageTaskNumEmpty, errorMessageTaskNumInvalid);
    }

    /**
     * Gets the task number from Unmark command.
     *
     * @param commandInfo Command information
     * @return The task number
     * @throws DukeException If the task number is empty or not a valid integer
     */
    private int getTaskNumFromUnmarkCommand(String commandInfo) throws DukeException {
        String errorMessageTaskNumEmpty = ErrorMessage.ERROR_UNMARK_TASK_NUM_EMPTY.toString();
        String errorMessageTaskNumInvalid = ErrorMessage.ERROR_UNMARK_TASK_NUM_INVALID.toString();
        return getTaskNumFromCommandInfo(commandInfo, errorMessageTaskNumEmpty, errorMessageTaskNumInvalid);
    }

    /**
     * Gets the task number from Delete command.
     *
     * @param commandInfo Command information
     * @return The task number
     * @throws DukeException If the task number is empty or not a valid integer
     */
    private int getTaskNumFromDeleteCommand(String commandInfo) throws DukeException {
        String errorMessageTaskNumEmpty = ErrorMessage.ERROR_DELETE_TASK_NUM_EMPTY.toString();
        String errorMessageTaskNumInvalid = ErrorMessage.ERROR_DELETE_TASK_NUM_INVALID.toString();
        return getTaskNumFromCommandInfo(commandInfo, errorMessageTaskNumEmpty, errorMessageTaskNumInvalid);
    }

    /**
     * Gets the task number from command information
     *
     * @param commandInfo Command information
     * @param errorMessageTaskNumEmpty Error message if the task number is empty
     * @param errorMessageTaskNumInvalid Error message if the task number is invalid
     * @return The task number
     * @throws DukeException If the task number is empty or not a valid integer
     */
    private int getTaskNumFromCommandInfo(String commandInfo, String errorMessageTaskNumEmpty,
                                          String errorMessageTaskNumInvalid) throws DukeException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new DukeException(errorMessageTaskNumEmpty);
        }

        try {
            return Integer.parseInt(commandInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(errorMessageTaskNumInvalid);
        }
    }

    /**
     * Gets the task description from ToDo command.
     *
     * @param commandInfo Command information
     * @return The task description
     * @throws DukeException If the task description is empty
     */
    private String getTaskDescriptionFromToDoCommand(String commandInfo) throws DukeException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new DukeException(ErrorMessage.ERROR_ADD_TODO_DESC_EMPTY.toString());
        }

        return commandInfo;
    }

    /**
     * Gets the task information from Deadline command.
     *
     * @param commandInfo Command information
     * @return The task information
     * @throws DukeException If the command information is incomplete
     * or has wrong date/time command
     */
    private String getTaskInfoFromDeadlineCommand(String commandInfo) throws DukeException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new DukeException(ErrorMessage.
                    ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DESC_AND_DATETIME_EMPTY.toString());
        }

        String[] taskInfoParts = commandInfo.split("/", 2);

        if (taskInfoParts.length != 2) {
            throw new DukeException(ErrorMessage.
                    ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DATETIME_EMPTY.toString());
        }

        String taskDescription = taskInfoParts[0];
        String taskDateTime = taskInfoParts[1];

        if (!taskDateTime.startsWith(DATETIME_COMMAND_FOR_DEADLINE)) {
            if (!isTaskDescriptionPresent(taskDescription)) {
                throw new DukeException(ErrorMessage.
                        ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DESC_EMPTY_AND_WRONG_COMMAND_DATETIME.toString());
            }

            throw new DukeException(ErrorMessage.
                    ERROR_ADD_DEADLINE_WRONG_COMMAND_DATETIME.toString());
        }

        String dateTime = taskDateTime.trim();
        String[] dateTimeParts = dateTime.split("\\s+", 2);

        if (dateTimeParts.length != 2) {
            if (!isTaskDescriptionPresent(taskDescription)) {
                throw new DukeException(ErrorMessage.
                        ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DESC_AND_DATETIME_EMPTY.toString());
            }

            throw new DukeException(ErrorMessage.
                    ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DATETIME_EMPTY.toString());
        }

        if (!isTaskDescriptionPresent(taskDescription)) {
            throw new DukeException(ErrorMessage.
                    ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DESC_EMPTY.toString());
        }

        return commandInfo;
    }

    /**
     * Gets the task information from Event command.
     *
     * @param commandInfo Command information
     * @return The task information
     * @throws DukeException If the command information is incomplete
     * or has wrong date/time command
     */
    private String getTaskInfoFromEventCommand(String commandInfo) throws DukeException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new DukeException(ErrorMessage.
                    ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DESC_AND_DATETIME_EMPTY.toString());
        }

        String[] taskInfoParts = commandInfo.split("/", 2);

        if (taskInfoParts.length != 2) {
            throw new DukeException(ErrorMessage.
                    ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DATETIME_EMPTY.toString());
        }

        String taskDescription = taskInfoParts[0];
        String taskDateTime = taskInfoParts[1];

        if (!taskDateTime.startsWith(DATETIME_COMMAND_FOR_EVENT)) {
            if (!isTaskDescriptionPresent(taskDescription)) {
                throw new DukeException(ErrorMessage.
                        ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DESC_EMPTY_AND_WRONG_COMMAND_DATETIME.toString());
            }

            throw new DukeException(ErrorMessage.
                    ERROR_ADD_EVENT_WRONG_COMMAND_DATETIME.toString());
        }

        String dateTime = taskDateTime.trim();
        String[] dateTimeParts = dateTime.split("\\s+", 2);

        if (dateTimeParts.length != 2) {
            if (!isTaskDescriptionPresent(taskDescription)) {
                throw new DukeException(ErrorMessage.
                        ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DESC_AND_DATETIME_EMPTY.toString());
            }

            throw new DukeException(ErrorMessage.
                    ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DATETIME_EMPTY.toString());
        }

        if (!isTaskDescriptionPresent(taskDescription)) {
            throw new DukeException(ErrorMessage.
                    ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DESC_EMPTY.toString());
        }

        return commandInfo;
    }

    /**
     * Gets the date from Print command.
     *
     * @param commandInfo Command information
     * @return The date
     * @throws DukeException If the command information is incomplete
     * or has wrong date command
     */
    private String getDateFromPrintCommand(String commandInfo) throws DukeException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new DukeException(ErrorMessage.
                    ERROR_PRINT_INCOMPLETE_COMMAND.toString());
        }

        if (!commandInfo.trim().startsWith(DATE_COMMAND_FOR_PRINT)) {
            throw new DukeException(ErrorMessage.
                    ERROR_PRINT_WRONG_COMMAND_DATE.toString());
        }

        String[] specificDateParts = commandInfo.split("\\s+", 2);

        if (specificDateParts.length != 2) {
            throw new DukeException(ErrorMessage.
                    ERROR_PRINT_INCOMPLETE_COMMAND_DATE_EMPTY.toString());
        }

        return specificDateParts[1];
    }

    /**
     * Gets the keyword from Find command.
     *
     * @param commandInfo Command information
     * @return The keyword
     * @throws DukeException If the keyword is empty
     */
    private String getKeywordFromFindCommand(String commandInfo) throws DukeException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new DukeException(ErrorMessage.ERROR_FIND_KEYWORD_EMPTY.toString());
        }

        return commandInfo;
    }

    /**
     * Checks if the command information is present.
     *
     * @param commandInfo Command information
     * @return True if command information is present, false otherwise
     */
    private boolean isCommandInfoPresent(String commandInfo) {
        return commandInfo.trim().length() > 0;
    }

    /**
     * Checks if the task description is present.
     *
     * @param taskDescription Task description
     * @return True if task description is present, false otherwise
     */
    private boolean isTaskDescriptionPresent(String taskDescription) {
        return taskDescription.trim().length() > 0;
    }
}
