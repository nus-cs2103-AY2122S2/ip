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
import duke.commands.Unmark;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.TaskException;
import tasks.TaskList;

/**
 * Represents a parser object that parses commands that the user types into the GUI
 */
public class Parser {
    enum CommandType {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND, LIST, BYE;

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
     * Method that takes in a command Type and index and performs the appropriate task action
     * @param commandType type of command user has inputted (i.e. bye, list...)
     * @param index taskId
     * @throws DukeException if task cannot be found within the tasklist
     * @throws NumberFormatException if taskId is not a number.
     */
    public Command taskAction(CommandType commandType, String... index) throws DukeException, NumberFormatException {
        String taskIdParameter = index[0];
        try {
            int taskId = Integer.parseInt(taskIdParameter);
            boolean withinBoundary = taskId > 0 && taskId < (TaskList.getTaskSize() + 1);
            if (!(withinBoundary)) {
                throw new TaskException("UNFOUND_TASK");
            }

            switch (commandType) {
            case DELETE:
                return new Delete(taskId);
            case UNMARK:
                return new Unmark(taskId);
            case MARK:
                return new Mark(taskId);
            default:
                assert false : commandType;
                throw new InvalidCommandException();
            }

        } catch (NumberFormatException e) {
            throw new TaskException("INVALID_TASKID");
        }
    }

    /**
     * Method that parses the string input and performs the correct action
     * @param input user input into the command lines
     * @return a Command that can be executed to perform an action
     * @throws DukeException if the command is invalid
     */
    public Command parseInput(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        CommandType commandType = CommandType.getCommandType(inputArray[0]);

        if (inputArray.length == 1) {
            return parseSingleCommand(commandType);
        }

        if (inputArray.length > 1) {
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

        assert false;
        throw new InvalidCommandException();
    }

    /**
     * Method that helps to parse multi-commands (for eg. todo 1, event eat /at 12-12-2021)
     * @param commandType CommandType that user has keyed in
     * @param taskId index of task (used for mark, find, delete)
     * @param taskDetails details of task (used for todo, event, deadline)
     * @return a Command that can be executed to perform the multi command action
     * @throws DukeException when the command is invalid
     */
    public Command parseMultiCommand(CommandType commandType, String taskId, String taskDetails) throws DukeException {
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

        switch (commandType) {
        case FIND:
            return new Find(taskId);
        case DELETE:
            return taskAction(CommandType.DELETE, taskId);
        case UNMARK:
            return taskAction(CommandType.UNMARK, taskId);
        case MARK:
            return taskAction(CommandType.MARK, taskId);
        case TODO:
            return new AddToDo(taskDetails);
        case DEADLINE:
            return new AddDeadline(description, date);
        case EVENT:
            return new AddEvent(description, dateTime);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Method that helps to parse single-commands (for eg. list, bye)
     * @param commandType Command that the user has keyed in
     * @return a Command that can be executed to perform the multi command action
     * @throws DukeException when the command is invalid
     */
    public Command parseSingleCommand(CommandType commandType) throws DukeException {
        switch (commandType) {
        case BYE:
            return new Bye();
        case LIST:
            return new List();
        default:
            throw new InvalidCommandException();
        }
    }

}


