package duke;
import tasks.*;

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

    public void taskAction(CommandType commandType, String index) throws DukeException, NumberFormatException {
        try {
            int taskId = Integer.parseInt(index);
            if (!(taskId > 0 && taskId < (TaskList.getTaskSize() + 1))) {
                throw new DukeException("tasks.Task cannot be found within the task list! Please fix your machigai!");
            }

            switch (commandType) {
                case DELETE:
                    TaskList.deleteTask(taskId);
                    break;
                case UNMARK:
                    TaskList.markTask(taskId, false);
                    break;
                case MARK:
                    TaskList.markTask(taskId, true);
                    break;
            }

        } catch (NumberFormatException e) {
            throw new DukeException("tasks.Task ID has to be an integer!");
        }

    }


    public boolean parseInput(String input) throws DukeException {
        String[] inputArray = input.split(" ");

        // single command
        if (inputArray.length == 1) {
            if (inputArray[0].equalsIgnoreCase("list")) {
                TaskList.listTasks();
            }
            else if  (inputArray[0].equalsIgnoreCase("bye")) {
                return true;
            }
            else {
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

            String taskDetails = taskDetailsBuilder.toString();

            String description = "";
            String date = "";
            String dateTime = "";

            if (taskDetails.contains("/by")) {
                description = taskDetails.split("/by", 2)[0];
                date = taskDetails.split("/by", 2)[1].substring(1);
            } else if (taskDetails.contains("/at")) {
                description = taskDetails.split("/at", 2)[0];
                dateTime = taskDetails.split("/at", 2)[1].substring(1);
            }

            switch (commandType) {
                case FIND:
                    TaskList.findTask(inputArray[1]);
                    break;
                case DELETE:
                    taskAction(CommandType.DELETE, inputArray[1]);
                    break;
                case UNMARK:
                    taskAction(CommandType.UNMARK, inputArray[1]);
                    break;
                case MARK:
                    taskAction(CommandType.MARK, inputArray[1]);
                    break;
                case TODO:
                    Task ToDo = new Todo(taskDetails);
                    if (taskDetails.equals("")) {
                        throw new DukeException("tasks.Todo command is invalid!");
                    }
                    TaskList.addTask(ToDo);
                    break;
                case DEADLINE:
                    if (description.equals("") || date.equals("")) {
                        throw new DukeException("tasks.Deadline command is invalid!");
                    }
                    Task Deadline = new Deadline(description, date);
                    TaskList.addTask(Deadline);
                    break;
                case EVENT:
                    if (dateTime.equals("") || description.equals("")) {
                        throw new DukeException("tasks.Event command is invalid");
                    }
                    Task Event = new Event(description, dateTime);
                    TaskList.addTask(Event);
                    break;
                default:
                    throw new DukeException("Sumimasen! I don't recognize this command. Please try again!");
            }
        }

        return false;
    }
}