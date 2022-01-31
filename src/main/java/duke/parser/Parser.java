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

/**
 * A class that handles the logic of parsing the user command.
 */
public class Parser {
    public Command parse(String commandLine) throws DukeException {
        String[] commandLineParts = commandLine.split("\\s+", 2);

        CommandType commandType = validateCommand(commandLineParts[0].toLowerCase());

        if (commandType == null) {
            throw new DukeException("INVALID COMMAND. Please try again!");
        }

        String commandInfo;

        if (commandLineParts.length == 2) {
            commandInfo = commandLineParts[1];

            // If user inputs extra information for "bye" or "list" commands,
            // then it is not a valid command
            if ((commandType == CommandType.BYE) || (commandType == CommandType.LIST)) {
                throw new DukeException("INVALID COMMAND. Please try again!");
            }
        } else {
            // For "bye" and "list" commands
            commandInfo = "";
        }

        return initialiseCommand(commandType, commandInfo);
    }

    private CommandType validateCommand(String commandType) {
        for (CommandType c : CommandType.values()) {
            if (c.getLabel().equals(commandType)) {
                return c;
            }
        }

        return null;
    }

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
            taskInfo = processTaskInfoFromDeadlineCommand(commandInfo);
            command = new AddDeadlineCommand(taskInfo);
            break;
        case EVENT:
            taskInfo = processTaskInfoFromEventCommand(commandInfo);
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
            throw new DukeException("INVALID COMMAND. Please try again!");
        }

        return command;
    }

    private boolean isCommandInfoPresent(String commandInfo) {
        return commandInfo.trim().length() > 0;
    }

    private boolean isTaskDescriptionPresent(String taskDescription) {
        return taskDescription.trim().length() > 0;
    }

    private int getTaskNumFromMarkCommand(String commandInfo) throws DukeException {
        if (isCommandInfoPresent(commandInfo)) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to be marked as done!");
            }
        } else {
            throw new DukeException("Please enter a task number to be marked as done!");
        }
    }

    private int getTaskNumFromUnmarkCommand(String commandInfo) throws DukeException {
        if (isCommandInfoPresent(commandInfo)) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to be marked as not done yet!");
            }
        } else {
            throw new DukeException("Please enter a task number to be marked as not done yet!");
        }
    }

    private int getTaskNumFromDeleteCommand(String commandInfo) throws DukeException {
        if (isCommandInfoPresent(commandInfo)) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to be deleted!");
            }
        } else {
            throw new DukeException("Please enter a task number to be deleted!");
        }
    }

    private String getTaskDescriptionFromToDoCommand(String commandInfo) throws DukeException {
        if (isCommandInfoPresent(commandInfo)) {
            return commandInfo;
        } else {
            throw new DukeException("The description of a todo cannot be empty!");
        }
    }

    private String processTaskInfoFromDeadlineCommand(String commandInfo) throws DukeException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new DukeException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of a deadline cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Enter /by before specifying the date/time]");
        } else {
            String[] taskInfoParts = commandInfo.split("/", 2);

            if (taskInfoParts.length != 2) {
                throw new DukeException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of a deadline cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /by before specifying the date/time]");
            } else {
                return getTaskInfoFromDeadlineCommand(commandInfo, taskInfoParts);
            }
        }
    }

    private String getTaskInfoFromDeadlineCommand(String commandInfo, String[] taskInfoParts) throws DukeException {
        String taskDescription = taskInfoParts[0];
        String taskDateTime = taskInfoParts[1];

        if (taskDateTime.startsWith("by")) {
            String dateTime = taskDateTime.trim();
            String[] dateTimeParts = dateTime.split("\\s+", 2);

            if (dateTimeParts.length == 2) {
                if (isTaskDescriptionPresent(taskDescription)) {
                    return commandInfo;
                } else {
                    throw new DukeException("INCOMPLETE COMMAND"
                            + System.lineSeparator() + "\t"
                            + "The description of a deadline cannot be empty!");
                }
            } else {
                if (isTaskDescriptionPresent(taskDescription)) {
                    throw new DukeException("INCOMPLETE COMMAND"
                            + System.lineSeparator() + "\t"
                            + "The date/time of a deadline cannot be empty!"
                            + System.lineSeparator() + "\t"
                            + "[Note: Enter /by before specifying the date/time]");
                } else {
                    throw new DukeException("INCOMPLETE COMMAND"
                            + System.lineSeparator() + "\t"
                            + "The description of a deadline cannot be empty!"
                            + System.lineSeparator() + "\t"
                            + "The date/time of a deadline cannot be empty!"
                            + System.lineSeparator() + "\t"
                            + "[Note: Enter /by before specifying the date/time]");
                }
            }
        } else {
            if (isTaskDescriptionPresent(taskDescription)) {
                throw new DukeException("WRONG COMMAND"
                        + System.lineSeparator() + "\t"
                        + "Enter /by before specifying the date/time!");
            } else {
                throw new DukeException("INCOMPLETE & WRONG COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The description of a deadline cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "Enter /by before specifying the date/time!");
            }
        }
    }

    private String processTaskInfoFromEventCommand(String commandInfo) throws DukeException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new DukeException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of an event cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Enter /at before specifying the date/time]");
        } else {
            String[] taskInfoParts = commandInfo.split("/", 2);

            if (taskInfoParts.length != 2) {
                throw new DukeException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of an event cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /at before specifying the date/time]");
            } else {
                return getTaskInfoFromEventCommand(commandInfo, taskInfoParts);
            }
        }
    }

    private String getTaskInfoFromEventCommand(String commandInfo, String[] taskInfoParts) throws DukeException {
        String taskDescription = taskInfoParts[0];
        String taskDateTime = taskInfoParts[1];

        if (taskDateTime.startsWith("at")) {
            String dateTime = taskDateTime.trim();
            String[] dateTimeParts = dateTime.split("\\s+", 2);

            if (dateTimeParts.length == 2) {
                if (isTaskDescriptionPresent(taskDescription)) {
                    return commandInfo;
                } else {
                    throw new DukeException("INCOMPLETE COMMAND"
                            + System.lineSeparator() + "\t"
                            + "The description of an event cannot be empty!");
                }
            } else {
                if (isTaskDescriptionPresent(taskDescription)) {
                    throw new DukeException("INCOMPLETE COMMAND"
                            + System.lineSeparator() + "\t"
                            + "The date/time of an event cannot be empty!"
                            + System.lineSeparator() + "\t"
                            + "[Note: Enter /at before specifying the date/time]");
                } else {
                    throw new DukeException("INCOMPLETE COMMAND"
                            + System.lineSeparator() + "\t"
                            + "The description of an event cannot be empty!"
                            + System.lineSeparator() + "\t"
                            + "The date/time of an event cannot be empty!"
                            + System.lineSeparator() + "\t"
                            + "[Note: Enter /at before specifying the date/time]");
                }
            }
        } else {
            if (isTaskDescriptionPresent(taskDescription)) {
                throw new DukeException("WRONG COMMAND"
                        + System.lineSeparator() + "\t"
                        + "Enter /at before specifying the date/time!");
            } else {
                throw new DukeException("INCOMPLETE & WRONG COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The description of an event cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "Enter /at before specifying the date/time!");
            }
        }
    }

    private String getDateFromPrintCommand(String commandInfo) throws DukeException {
        if (isCommandInfoPresent(commandInfo)) {
            if (commandInfo.trim().startsWith("/on")) {
                String[] specificDateParts = commandInfo.split("\\s+", 2);

                if (specificDateParts.length == 2) {
                    return specificDateParts[1];
                } else {
                    throw new DukeException("INCOMPLETE COMMAND"
                            + System.lineSeparator() + "\t"
                            + "The date is not specified!"
                            + System.lineSeparator() + "\t"
                            + "[Note: Enter /on before specifying the date]");
                }
            } else {
                throw new DukeException("WRONG COMMAND"
                        + System.lineSeparator() + "\t"
                        + "Enter /on before specifying the date!");
            }
        } else {
            throw new DukeException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "Enter /on before specifying the date!");
        }
    }

    private String getKeywordFromFindCommand(String commandInfo) throws DukeException {
        if (isCommandInfoPresent(commandInfo)) {
            return commandInfo;
        } else {
            throw new DukeException("Please enter the keyword to search for matching tasks!");
        }
    }
}
