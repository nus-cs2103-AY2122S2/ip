import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private final Ui ui;
    private Storage storage;
    private List<Task> tasks;

    public Duke(String folderName, String fileName) {
        ui = new Ui();

        try {
            storage = new Storage(folderName, fileName);
            tasks = storage.loadTasksFromFile();
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        new Duke("data", "tasks.txt").run();
    }

    public void run() {
        ui.displayWelcome();

        String commandLine = "";

        while (!commandLine.equals("bye")) {
            commandLine = ui.readCommand();

            try {
                processCommand(commandLine);
            } catch (DukeException | IOException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    public void processCommand(String commandLine) throws DukeException, IOException {
        String[] commandLineParts = commandLine.split(" ", 2);

        String commandType;
        String commandInfo;

        if (commandLineParts.length == 2) {
            commandType = commandLineParts[0];
            commandInfo = commandLineParts[1];

            // If user inputs extra text after "bye" or "list" commands
            if ((commandType.equals("bye") || commandType.equals("list")) && !commandInfo.equals("")) {
                throw new DukeException("INVALID COMMAND. Please try again!");
            }
        } else { // for "bye" and "list" commands
            commandType = commandLineParts[0];
            commandInfo = "";
        }

        executeCommand(commandType, commandInfo);
    }

    public void executeCommand(String commandType, String commandInfo) throws DukeException, IOException {
        int taskNum;
        Task task;
        String taskInfo;
        String taskDescription;
        String taskDateTime;

        switch (commandType.toLowerCase()) {
        case "bye":
            ui.displayExit();
            break;
        case "list":
            displayTasks(tasks);
            break;
        case "mark":
            taskNum = getTaskNumFromMarkCommand(commandInfo);
            markTask(tasks, taskNum);
            break;
        case "unmark":
            taskNum = getTaskNumFromUnmarkCommand(commandInfo);
            unmarkTask(tasks, taskNum);
        case "delete":
            taskNum = getTaskNumFromDeleteCommand(commandInfo);
            deleteTask(tasks, taskNum);
            break;
        case "todo":
            taskDescription = getTaskDescriptionFromToDoCommand(commandInfo);
            task = new ToDo(taskDescription);
            addTask(tasks, task);
            break;
        case "deadline":
            taskInfo = processTaskInfoFromDeadlineCommand(commandInfo);
            taskDescription = taskInfo.split("/", 2)[0].trim();
            String taskByDateTime = taskInfo.split("/", 2)[1].trim();
            taskDateTime = taskByDateTime.split("\\s+", 2)[1].trim();
            task = new Deadline(taskDescription, taskDateTime);
            addTask(tasks, task);
            break;
        case "event":
            taskInfo = processTaskInfoFromEventCommand(commandInfo);
            taskDescription = taskInfo.split("/", 2)[0].trim();
            String taskAtDateTime = taskInfo.split("/", 2)[1].trim();
            taskDateTime = taskAtDateTime.split("\\s+", 2)[1].trim();
            task = new Event(taskDescription, taskDateTime);
            addTask(tasks, task);
            break;
        default:
            throw new DukeException("INVALID COMMAND. Please try again!");
        }
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

    public void addTask(List<Task> tasks, Task task) throws IOException {
        tasks.add(task);

        String response = ui.taskAddedMessage(task)
                + System.lineSeparator()
                + ui.numOfTasksInListMessage(tasks);
        ui.displayResponse(response);

        storage.saveTasksToFile(tasks);
    }

    public void displayTasks(List<Task> tasks) throws DukeException {
        if (tasks.size() != 0) {
            String response = ui.tasksInListMessage(tasks);
            ui.displayResponse(response);
        } else {
            throw new DukeException("There are no tasks in your list!");
        }
    }

    public void markTask(List<Task> tasks, int taskNum) throws DukeException, IOException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            task.setDone();

            String message = ui.taskDoneMessage(task);
            ui.displayResponse(message);

            storage.saveTasksToFile(tasks);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }

    public void unmarkTask(List<Task> tasks, int taskNum) throws DukeException, IOException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            task.setNotDone();

            String message = ui.taskNotDoneMessage(task);
            ui.displayResponse(message);

            storage.saveTasksToFile(tasks);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }

    public void deleteTask(List<Task> tasks, int taskNum) throws DukeException, IOException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            tasks.remove(task);

            String message = ui.taskDeletedMessage(task)
                    + System.lineSeparator()
                    + ui.numOfTasksInListMessage(tasks);
            ui.displayResponse(message);

            storage.saveTasksToFile(tasks);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }
}
