import java.util.Arrays;

public class Parser {
    public static String exitResponse() {
        return "Bye. Hope to see you again soon!";
    }

    public static boolean isExit(String command) {
        if (command.equals("bye")) {
            return true;
        }
        return false;
    }

    public static String parseSavedToDoTask(ToDo task) {
        String taskName = task.getDescription();
        return String.join(" ", "ToDo",taskName);
    }

    public static String parseSavedEventTask(Event task) {
        String taskName = task.getDescription();
        String taskTime = task.getTime();
        return String.join(" ", "Event", taskName, "/at", taskTime);
    }

    public static String parseSavedDeadlineTask(Deadline task) {
        String taskName = task.getDescription();
        String taskTime = task.getTime();
        return String.join(" ", "Deadline", taskName, "/by", taskTime);
    }

    public static Task parseFile(String line) throws DukeInvalidFileException {
        try {
            String[] lineArguments = line.split(" ");
            String taskType = lineArguments[0];
            String taskName;
            boolean completed;
            if (lineArguments[lineArguments.length - 1].equals("done")) {
                completed = true;
            } else {
                completed = false;
            }
            lineArguments = Arrays.copyOf(lineArguments, lineArguments.length-1);
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
                default:
                    throw new DukeInvalidCommandException();
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
