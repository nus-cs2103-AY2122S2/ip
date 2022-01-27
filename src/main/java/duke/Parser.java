package duke;

import java.util.Arrays;

/**
 * Parser class to parse user commands and arguments into strings
 */
public class Parser {

    /**
     * Returns exit response when the exit command is invoked
     * @return String representing exit message
     */
    public static String exitResponse() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Determines if user command is the exit command
     * @param command User input
     * @return Returns true if user input is the exit command
     */
    public static boolean isExit(String command) {
        if (command.equals("bye")) {
            return true;
        }
        return false;
    }

    /**
     * Parses a <code>ToDo</code> object into a String to be saved to the backup file
     * @param task <code>ToDo</code> object to be parsed
     * @return String corresponding to the <code>ToDo</code> object
     */
    public static String parseSavedToDoTask(ToDo task) {
        String taskName = task.getDescription();
        return String.join(" ", "ToDo", taskName);
    }

    /**
     * Parses an <code>Event</code> object into a String to be saved to the backup file
     * @param task <code>Event</code> object to be parsed
     * @return String corresponding to the <code>Event</code> object
     */
    public static String parseSavedEventTask(Event task) {
        String taskName = task.getDescription();
        String taskTime = task.getTime();
        return String.join(" ", "Event", taskName, "/at", taskTime);
    }

    /**
     * Parses a <code>Deadline</code> object into a String to be saved to the backup file
     * @param task <code>Deadline</code> object to be parsed
     * @return String corresponding to the <code>Deadline</code> object
     */
    public static String parseSavedDeadlineTask(Deadline task) {
        String taskName = task.getDescription();
        String taskTime = task.getTime();
        return String.join(" ", "Deadline", taskName, "/by", taskTime);
    }

    /**
     * Parses a single line representing a <code>Task</code> found in the backup file
     * @param line A line representing a saved <code>Task</code> found in the backup file
     * @return <code>Task</code> represented by the String input found in the backup file
     * @throws DukeInvalidFileException Throws exception if file contents are invalid and cannot be parsed
     */
    public static Task parseFile(String line) throws DukeInvalidFileException {
        try {
            String[] lineArguments = line.split(" ");
            String taskType = lineArguments[0];
            String taskName;
            boolean completed;
            if (lineArguments[lineArguments.length - 1].equals("done")) {
                completed = true;
            } else if (lineArguments[lineArguments.length - 1].equals("undone")) {
                completed = false;
            } else {
                throw new DukeInvalidFileException();
            }
            lineArguments = Arrays.copyOf(lineArguments, lineArguments.length - 1);
            line = String.join(" ", lineArguments);
            switch (taskType) {
            case "ToDo":
                taskName = line.substring("ToDo".length() + 1).trim();
                return new ToDo(taskName, completed);
            case "Deadline":
                String[] deadlineDetails = line.split("/by", 2);
                String deadlineTime = deadlineDetails[1].trim();
                taskName = deadlineDetails[0].substring("deadline".length() + 1).trim();
                return new Deadline(taskName, deadlineTime, completed);
            case "Event":
                String[] eventDetails = line.split("/at", 2);
                String eventTime = eventDetails[1].trim();
                taskName = eventDetails[0].substring("event".length() + 1).trim();
                return new Event(taskName, eventTime, completed);
            default:
                throw new DukeInvalidFileException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidFileException();
        }
    }

    /**
     * Parses a command to mark a <code>Task</code>
     * @param commandArguments User input arguments supplied
     * @param taskManager Handle the marking, unmarking, deleting and adding of tasks
     * @return String representing the response by the Duke application as a result of the user command
     * @throws DukeException Handles any errors that occur during the function
     */
    public static String parseMarkCommand(String[] commandArguments, TaskManager taskManager) throws DukeException {
        String response = "";
        if (commandArguments.length < 2) {
            throw new DukeInsufficientArgumentsException();
        }
        try {
            int markIndex = Integer.parseInt(commandArguments[1]) - 1;
            response = taskManager.mark(markIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskNotFoundException();
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
        return response;
    }

    /**
     * Parses a command to unmark a <code>Task</code>
     * @param commandArguments User input arguments supplied
     * @param taskManager Handle the marking, unmarking, deleting and adding of tasks
     * @return String representing the response by the Duke application as a result of the user command
     * @throws DukeException Handles any errors that occur during the function
     */
    public static String parseUnmarkCommand(String[] commandArguments, TaskManager taskManager) throws DukeException {
        String response = "";
        if (commandArguments.length < 2) {
            throw new DukeInsufficientArgumentsException();
        }
        try {
            int unmarkIndex = Integer.parseInt(commandArguments[1]) - 1;
            response = taskManager.unmark(unmarkIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskNotFoundException();
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
        return response;
    }

    /**
     * Parses a command to delete a <code>Task</code>
     * @param commandArguments User input arguments supplied
     * @param taskManager Handle the marking, unmarking, deleting and adding of tasks
     * @return String representing the response by the Duke application as a result of the user command
     * @throws DukeException Handles any errors that occur during the function
     */
    public static String parseDeleteCommand(String[] commandArguments, TaskManager taskManager) throws DukeException {
        String response = "";
        if (commandArguments.length < 2) {
            throw new DukeInsufficientArgumentsException();
        }
        try {
            int deleteIndex = Integer.parseInt(commandArguments[1]) - 1;
            response = taskManager.delete(deleteIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskNotFoundException();
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
        return response;
    }

    /**
     * Parses a command to add a <code>ToDo</code> object
     * @param commandArguments User input arguments supplied
     * @param taskManager Handle the marking, unmarking, deleting and adding of tasks
     * @return String representing the response by the Duke application as a result of the user command
     * @throws DukeException Handles any errors that occur during the function
     */
    public static String parseTodoCommand(String[] commandArguments, TaskManager taskManager) throws DukeException {
        if (commandArguments.length < 2) {
            throw new DukeInsufficientArgumentsException();
        }
        String command = String.join(" ", commandArguments);
        String toDoName = command.substring("todo".length() + 1);
        ToDo toDoTask = new ToDo(toDoName);
        String response = taskManager.add(toDoTask);
        return response;
    }

    /**
     * Parses a command to add a <code>Deadline</code> object
     * @param commandArguments User input arguments supplied
     * @param taskManager Handle the marking, unmarking, deleting and adding of tasks
     * @return String representing the response by the Duke application as a result of the user command
     * @throws DukeException Handles any errors that occur during the function
     */
    public static String parseDeadlineCommand(String[] commandArguments, TaskManager taskManager) throws DukeException {
        if (commandArguments.length < 2) {
            throw new DukeInsufficientArgumentsException();
        }
        boolean foundDeadlineCommand = false;
        for (int i = 0; i < commandArguments.length; i++) {
            if (commandArguments[i].equals("/by")) {
                foundDeadlineCommand = true;
            }
        }
        if (!foundDeadlineCommand) {
            throw new DukeInsufficientArgumentsException();
        }
        String command = String.join(" ", commandArguments);
        String[] deadlineDetails = command.split("/by", 2);
        String deadlineTime = deadlineDetails[1].trim();
        String deadlineName = deadlineDetails[0].substring("deadline".length() + 1).trim();
        if (deadlineTime.isEmpty()) {
            throw new DukeInsufficientArgumentsException();
        }
        if (deadlineName.isEmpty()) {
            throw new DukeInsufficientArgumentsException();
        }
        Deadline deadlineTask = new Deadline(deadlineName, deadlineTime);
        String response = taskManager.add(deadlineTask);
        return response;
    }

    /**
     * Parses a command to add an <code>Event</code> object
     * @param commandArguments User input arguments supplied
     * @param taskManager Handle the marking, unmarking, deleting and adding of tasks
     * @return String representing the response by the Duke application as a result of the user command
     * @throws DukeException Handles any errors that occur during the function
     */
    public static String parseEventCommand(String[] commandArguments, TaskManager taskManager) throws DukeException {
        if (commandArguments.length < 2) {
            throw new DukeInsufficientArgumentsException();
        }
        boolean foundEventCommand = false;
        for (int i = 0; i < commandArguments.length; i++) {
            if (commandArguments[i].equals("/at")) {
                foundEventCommand = true;
            }
        }
        if (!foundEventCommand) {
            throw new DukeInsufficientArgumentsException();
        }
        String command = String.join(" ", commandArguments);
        String[] eventDetails = command.split("/at", 2);
        String eventTime = eventDetails[1].trim();
        String eventName = eventDetails[0].substring("event".length() + 1).trim();
        if (eventTime.isEmpty()) {
            throw new DukeInsufficientArgumentsException();
        }
        if (eventName.isEmpty()) {
            throw new DukeInsufficientArgumentsException();
        }
        Event eventTask = new Event(eventName, eventTime);
        String response = taskManager.add(eventTask);
        return response;
    }

    /**
     * Parses a command to find tasks matching the description string
     * @param commandArguments User input arguments supplied
     * @param taskManager Handle the marking, unmarking, deleting and adding of tasks
     * @return String representing the response by the Duke application as a result of the user command
     * @throws DukeException Handles any errors that occur during the function
     */
    public static String parseFindCommand(String[] commandArguments, TaskManager taskManager) throws DukeException {
        if (commandArguments.length < 2) {
            throw new DukeInsufficientArgumentsException();
        }
        String command = String.join(" ", commandArguments);
        String queryName = command.substring("find".length() + 1);
        String response = taskManager.find(queryName);
        return response;
    }

    /**
     * Parses a command to find tasks matching a given date
     * @param commandArguments User input arguments supplied
     * @param taskManager Handle the marking, unmarking, deleting and adding of tasks
     * @return String representing the response by the Duke application as a result of the user command
     * @throws DukeException Handles any errors that occur during the function
     */
    public static String parseFindByDateCommand(String[] commandArguments,
            TaskManager taskManager) throws DukeException {
        if (commandArguments.length < 2) {
            throw new DukeInsufficientArgumentsException();
        }
        String command = String.join(" ", commandArguments);
        String queryName = command.substring("date".length() + 1);
        String response = taskManager.findByDate(queryName);
        return response;
    }

    /**
     * Parses user input and invokes other functions depending on the type of user command
     * @param command User input
     * @param taskManager Handle the marking, unmarking, deleting and adding of tasks
     * @return String representing the response by the Duke application as a result of the user command
     */
    public static String parseCommand(String command, TaskManager taskManager) {
        try {
            if (command.equals("bye")) {
                return exitResponse();
            }
            String[] commandArguments = command.split(" ");
            if (command.isEmpty() || commandArguments.length == 0) {
                throw new DukeEmptyInputException();
            }
            if (commandArguments[0].equals("")) {
                throw new DukeEmptyInputException();
            }
            String operation = commandArguments[0];
            switch (operation) {
            case "list":
                return taskManager.list();
            case "mark":
                return parseMarkCommand(commandArguments, taskManager);
            case "unmark":
                return parseUnmarkCommand(commandArguments, taskManager);
            case "delete":
                return parseDeleteCommand(commandArguments, taskManager);
            case "event":
                return parseEventCommand(commandArguments, taskManager);
            case "todo":
                return parseTodoCommand(commandArguments, taskManager);
            case "deadline":
                return parseDeadlineCommand(commandArguments, taskManager);
            case "find":
                return parseFindCommand(commandArguments, taskManager);
            case "date":
                return parseFindByDateCommand(commandArguments, taskManager);
            default:
                throw new DukeInvalidCommandException();
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
