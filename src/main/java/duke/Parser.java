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
import tasks.TaskList;

/**
 * Represents a parser object that parses commands that the user types into the GUI
 */
public class Parser {
    enum CommandType {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND;

        static CommandType getCommandType(String input) throws DukeException {
            for (CommandType type : CommandType.values()) {
                if (input.equalsIgnoreCase(type.toString())) {
                    return type;
                }
            }
            throw new DukeException("Sumimasen! I don't recognize this command. Please try again!");
        }
    }

    /**
     * Method that takes in a command Type and index and performs the appropriate task action
     * @param commandType type of command user has inputted (i.e. bye, list...)
     * @param index taskId
     * @throws DukeException if task cannot be found within the tasklist
     * @throws NumberFormatException if taskId is not a number.
     */
    public Command taskAction(CommandType commandType, String index) throws DukeException, NumberFormatException {
        try {
            int taskId = Integer.parseInt(index);
            if (!(taskId > 0 && taskId < (TaskList.getTaskSize() + 1))) {
                throw new DukeException("Task cannot be found within the task list! Please fix your machigai!");
            }

            switch (commandType) {
            case DELETE:
                return new Delete(taskId);
            case UNMARK:
                return new Unmark(taskId);
            case MARK:
                return new Mark(taskId);
            default:
                assert false: commandType;
                throw new DukeException("Invalid Command Type!");
            }

        } catch (NumberFormatException e) {
            throw new DukeException("Task ID has to be an integer!");
        }

    }

    /**
     * Method that parses the string input and performs the correct action
     * @param input user input into the command lines
     * @return true if the command that has been inputted equals "bye", else return false
     * @throws DukeException if the command is invalid
     */
    public Command parseInput(String input) throws DukeException {
        String[] inputArray = input.split(" ");

        // single command
        if (inputArray.length == 1) {
            if (inputArray[0].equalsIgnoreCase("list")) {
                return new List();
            } else if (inputArray[0].equalsIgnoreCase("bye")) {
                return new Bye();
            } else {
                throw new DukeException("Sumimasen! I don't recognize this command. Please try again!");
            }
        }

        // multi command
        if (inputArray.length > 1) {
            CommandType commandType = CommandType.getCommandType(inputArray[0]);
            StringBuilder taskDetailsBuilder = new StringBuilder();

            for (int i = 1; i < inputArray.length; i++) {
                if (i != inputArray.length - 1) {
                    taskDetailsBuilder.append(inputArray[i]).append(" ");
                } else {
                    taskDetailsBuilder.append(inputArray[i]);
                }
            }

            // task variables
            String taskDetails = taskDetailsBuilder.toString();
            String description = "";
            String date = "";
            String dateTime = "";

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
                return new Find(inputArray[1]);
            case DELETE:
                return taskAction(CommandType.DELETE, inputArray[1]);
            case UNMARK:
                return taskAction(CommandType.UNMARK, inputArray[1]);
            case MARK:
                return taskAction(CommandType.MARK, inputArray[1]);
            case TODO:
                return new AddToDo(taskDetails);
            case DEADLINE:
                return new AddDeadline(description, date);
            case EVENT:
                return new AddEvent(description, dateTime);
            default:
                assert false: commandType.toString();
                throw new DukeException("Sumimasen! I don't recognize this command. Please try again!");
            }
        }

        assert false;
        return new Command();
    }
}
