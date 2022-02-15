package duke;

import duke.commands.AddDeadline;
import duke.commands.AddEvent;
import duke.commands.AddToDo;
import duke.commands.Bye;
import duke.commands.Command;
import duke.commands.Delete;
import duke.commands.Find;
import duke.commands.List;
import duke.commands.Mark;
import duke.commands.Undo;
import duke.commands.Unmark;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.TaskException;
import duke.tasks.TaskList;

/**
 * Represents a parser object that parses commands that the user types into the GUI.
 */
public class Parser {
    private Command previousCommand;
    enum CommandType {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND, LIST, BYE, UNDO;

        static CommandType getCommandType(String input) throws DukeException {
            for (CommandType type : CommandType.values()) {
                if (input.equalsIgnoreCase(type.toString())) {
                    return type;
                }
            }
            throw new InvalidCommandException();
        }
    }

    /**
     * Returns a command based on the command type that the user has keyed in.
     * This method is specific to command types that require index manipulation.
     * (For e.g. Delete, mark and un-mark commands).
     * @param commandType Type of command the the user has keyed in.
     * @param index Index of a task.
     * @throws TaskException if task index provided is not a number or if the task cannot be
     * found within the task list.
     * @throws InvalidCommandException if the commandType provided is invalid.
     */
    private Command taskAction(CommandType commandType, String... index) throws InvalidCommandException, TaskException {
        String taskIdParameter = index[0];
        try {
            int taskId = Integer.parseInt(taskIdParameter);
            boolean withinBoundary = taskId > 0 && taskId < (TaskList.getTaskSize() + 1);
            if (!(withinBoundary)) {
                throw new TaskException("UNFOUND_TASK");
            }

            Command currentCommand;
            switch (commandType) {
            case DELETE:
                currentCommand = new Delete(taskId);
                previousCommand = currentCommand;
                break;
            case UNMARK:
                currentCommand = new Unmark(taskId);
                previousCommand = currentCommand;
                break;
            case MARK:
                currentCommand = new Mark(taskId);
                previousCommand = currentCommand;
                break;
            default:
                assert false : commandType;
                throw new InvalidCommandException();
            }
            return currentCommand;

        } catch (NumberFormatException e) {
            throw new TaskException("INVALID_TASKID");
        }
    }

    /**
     * Returns a command based on the input the user has parsed into the system.
     * Throws an exception if the input that the user has parsed is unreadable
     * by the system.
     * @param input Input that the user has keyed into the GUI commandline.
     * @return A command that can be executed to perform an action.
     * @throws DukeException if the string input provided by the user cannot
     * be interpreted by the system.
     */
    public Command parseInput(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        CommandType commandType = CommandType.getCommandType(inputArray[0]);

        if (inputArray.length == 1) {
            return parseSingleCommand(commandType);
        }


        StringBuilder taskDetailsBuilder = new StringBuilder();

        for (int i = 1; i < inputArray.length; i++) {
            if (i != inputArray.length - 1) {
                taskDetailsBuilder.append(inputArray[i]).append(" ");
            } else {
                taskDetailsBuilder.append(inputArray[i]);
            }
        }
        String taskDetails = taskDetailsBuilder.toString();


        return parseMultiCommand(commandType, inputArray[1], taskDetails);
    }

    /**
     * Returns a command for an input that contains more than 1 input words.
     * These commands include mark, find, delete, as well as all add commands.
     * @param commandType CommandType of the command.
     * @param taskId Index of task (used for mark, find, delete).
     * @param taskDetails Details of task (used for todo, event, deadline).
     * @return A Command that can be executed. This command is dependent on the commandType
     * that has been provided to the method.
     * @throws DukeException if the commandType that has been provided is an invalid command.
     */
    private Command parseMultiCommand(CommandType commandType, String taskId, String taskDetails) throws DukeException {
        String description = "";
        String date = "";
        String dateTime = "";

        // Process Event & Deadline commands
        if (taskDetails.contains("/by")) {
            try {
                description = taskDetails.split("/by", 2)[0];
                date = taskDetails.split("/by", 2)[1].substring(1);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Missing deadline date or description!");
            }
        } else if (taskDetails.contains("/at")) {
            try {
                description = taskDetails.split("/at", 2)[0];
                dateTime = taskDetails.split("/at", 2)[1].substring(1);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Missing event date or description!");
            }
        }

        Command currentCommand;
        switch (commandType) {
        case FIND:
            currentCommand = new Find(taskId);
            previousCommand = currentCommand;
            break;
        case DELETE:
            return taskAction(CommandType.DELETE, taskId);
        case UNMARK:
            return taskAction(CommandType.UNMARK, taskId);
        case MARK:
            return taskAction(CommandType.MARK, taskId);
        case TODO:
            currentCommand = new AddToDo(taskDetails);
            previousCommand = currentCommand;
            break;
        case DEADLINE:
            currentCommand = new AddDeadline(description, date);
            previousCommand = currentCommand;
            break;
        case EVENT:
            currentCommand = new AddEvent(description, dateTime);
            previousCommand = currentCommand;
            break;
        default:
            throw new InvalidCommandException();
        }
        return currentCommand;

    }


    /**
     * Returns a command for an input that contains only one input word.
     * These commands are specific to list, bye and undo.
     * @param commandType CommandType of the command.
     * @return A Command that can be executed. This command is dependent on the commandType
     * that has been provided to the method.
     * @throws DukeException if the commandType that has been provided is an invalid command.
     */
    private Command parseSingleCommand(CommandType commandType) throws DukeException {
        switch (commandType) {
        case UNDO:
            return new Undo(previousCommand);
        case BYE:
            return new Bye();
        case LIST:
            return new List();
        default:
            throw new InvalidCommandException();
        }
    }

}


