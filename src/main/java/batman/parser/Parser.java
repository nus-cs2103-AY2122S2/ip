package batman.parser;

import batman.exception.DukeException;
import batman.exception.Error;
import batman.tasks.Deadline;
import batman.tasks.Event;
import batman.tasks.Task;
import batman.tasks.TaskList;
import batman.tasks.Todo;

public class Parser {

    /**
     * Returns a StringBuilder by parsing user
     * input and decides next action to take based on it.
     *
     * @param input User input.
     * @return StringBuilder object of result from user's input.
     */
    public static StringBuilder parseInput(String input) {
        StringBuilder sb = new StringBuilder();
        String[] command = input.split(" ", 2);
        try {
            if (input.equals("")) {
                throw new DukeException(Error.EMPTY_COMMAND);
            }
            switch (command[0]) {
            case "list":
                sb.append(TaskList.printList());
                break;
            case "mark":
            case "unmark":
                checkValidIndex(command);
                sb.append(updateStatus(command[0], Integer.parseInt(command[1]) - 1));
                break;
            case "todo":
                checkValid(command);
                sb.append(splitTask(command));
                break;
            case "event":
                checkValidEvent(command);
                sb.append(splitTask(command));
                break;
            case "deadline":
                checkValidDeadline(command);
                sb.append(splitTask(command));
                break;
            case "delete":
                checkValidIndex(command);
                sb.append(TaskList.deleteTask(Integer.parseInt(command[1]) - 1));
                break;
            case "find":
                checkValid(command);
                sb.append(TaskList.findTask(command[1]));
                break;
            default:
                throw new DukeException(Error.INVALID_COMMAND);
            }
        } catch (DukeException e) {
            sb.append(e.invalidInput());
            sb.append(e.invalidIndex());
            sb.append(e.invalidArg());
            sb.append(e.invalidEvent());
            sb.append(e.invalidDeadline());
            sb.append(e.emptyCommand());
        }
        return sb;
    }

    /**
     * Throws an exception if invalid argument is provided.
     *
     * @param command User input.
     */
    public static void checkValid(String[] command) throws DukeException {
        if (command.length <= 1 || command[1].equals("")) {
            throw new DukeException(Error.INVALID_ARG);
        }
    }

    /**
     * Throws an exception if invalid event is provided.
     *
     * @param command User input.
     */
    public static void checkValidEvent(String[] command) throws DukeException {
        if (command.length <= 1 || command[1].equals("") || !command[1].contains("/at")) {
            throw new DukeException(Error.INVALID_EVENT);
        }
    }

    /**
     * Throws an exception if invalid deadline is provided.
     *
     * @param command User input.
     */
    public static void checkValidDeadline(String[] command) throws DukeException {
        if (command.length <= 1 || command[1].equals("") || !command[1].contains("/by")) {
            throw new DukeException(Error.INVALID_DEADLINE);
        }
    }

    /**
     * Throws an exception if a digit is not provided.
     *
     * @param command User input.
     */
    public static void checkValidIndex(String[] command) throws DukeException {
        if (command.length <= 1 || command[1].equals("")
                || !command[1].matches("-?\\d+")) {
            throw new DukeException(Error.INVALID_DIGIT);
        }

    }

    /**
     * Returns a String object by parsing user
     * input and adding it to tasklist.
     *
     * @param command User input.
     * @return String object of added task.
     */
    public static String splitTask(String[] command) {
        String task;
        Task t;
        String description;
        String details;
        task = command[1];
        switch (command[0]) {
        case "deadline":
            description = task.split(" /")[0];
            details = task.split("/by ")[1];
            t = new Deadline(description, details);
            break;
        case "event":
            description = task.split(" /")[0];
            details = task.split("/at ")[1];
            t = new Event(description, details);
            break;
        default:
            description = task;
            t = new Todo(description);
        }
        return TaskList.addTask(t);
    }

    /**
     * Returns a String object by parsing user
     * input and updating status of task.
     *
     * @param action User input.
     * @param index Index of existing task in list.
     * @return String object of added task.
     */
    public static String updateStatus(String action, int index) {
        String result;
        switch (action) {
        case "mark":
            result = TaskList.markStatus(index);
            break;
        default:
            result = TaskList.unmarkStatus(index);
            break;
        }
        return result;
    }
}
