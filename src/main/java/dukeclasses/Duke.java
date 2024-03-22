package dukeclasses;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represent a program that reads commands from user input and logs them into tasks that are
 * stored for tracking whenever the program is booted up.
 */
public class Duke extends Application {
    private static final String TEXT_DATA_FILE_PATH = "data.txt";

    private boolean isExit = false;

    private final Storage storage;
    private final OutputString outputString;
    private TaskList tasks;
    private Gui gui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        outputString = new OutputString();
        storage = new Storage(TEXT_DATA_FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException errorMessage) {
            tasks = new TaskList();
        }
    }

    /**
     * Executes different commands based on command input and return the string which is the output.
     *
     * @param command command that was parsed from user input.
     * @return output which is the String to be printed  in the GUI as Duke's response.
     */
    private String generateOutput(ParsedInput command) {
        assert command != null : "command should not be null";
        switch (command.getCommand()) {
        case "hi":
            return outputString.greet();
        case "bye":
            isExit = true;
            return outputString.sayBye();
        case "list":
            return outputString.listTask(TEXT_DATA_FILE_PATH);
        case "mark":
        case "unmark":
        case "recur":
            return executeChangeOfStatusCommand(command);
        case "todo":
        case "event":
        case "deadline":
            return executeNewTaskCommand(command);
        case "delete":
            return executeDeleteTaskCommand(command);
        case "find":
            return executeFindCommand(command);
        default:
            return outputString.showInputError();
        }
    }

    /**
     * Parses the user input.
     *
     * @return ParsedCommand that represent the user input.
     */
    private ParsedCommand parseUserInput(String stringUserInput) {
        assert stringUserInput != null : "UserInput should not be null";
        try {
            ParsedCommand parsedInput = Parser.parseUserInput(stringUserInput, tasks.getTaskList().size());
            return parsedInput;
        } catch (DukeException errorMessage) {
            return null;
        }
    }

    /**
     * Execute methods related to the find command.
     *
     * @param command command that contains information for find command to execute.
     * @return String output that is printed in the GUI as Duke's response.
     */
    private String executeFindCommand(ParsedInput command) {
        assert command != null : "command should not be null.";
        assert command.getTask() != null : "command Task should not be null.";
        String taskDescription = command.getTask();
        TaskList findTaskList = tasks.findInTaskList(taskDescription);
        return outputString.listTaskUsingArrayList(findTaskList);
    }

    /**
     * Execute methods related to the commands that change status of the task.
     *
     * @param command command that contains information for change of status command to execute.
     * @return String output that is printed in the GUI as Duke's response.
     */
    private String executeChangeOfStatusCommand(ParsedInput command) {
        Task modifiedTask;
        if (command.getCommand().equals("mark")) {
            modifiedTask = tasks.updateTask(command.getIndex(), true);
        } else if (command.getCommand().equals("unmark")) {
            modifiedTask = tasks.updateTask(command.getIndex(), false);
        } else if (command.getCommand().equals("recur")) {
            modifiedTask = tasks.recur(command.getIndex());
        } else {
            return outputString.showInputError();
        }
        assert modifiedTask != null : "Task should not be null.";

        if (!updateItemsInStorage()) {
            return outputString.showStorageError();
        }
        return outputString.identifyTask(command, modifiedTask);
    }

    /**
     * Execute methods related to the commands that deletes task.
     *
     * @param command command that contains information for delete command to execute.
     * @return String output that is printed in the GUI as Duke's response.
     */
    private String executeDeleteTaskCommand(ParsedInput command) {
        Task deletedTask;
        try {
            deletedTask = tasks.deleteTask(command.getIndex());
        } catch (DukeException errorMessage) {
            return outputString.showInputError();
        }

        if (!updateItemsInStorage()) {
            return outputString.showStorageError();
        }

        return outputString.deleteTask(deletedTask);
    }

    /**
     * Execute methods related to the commands that creates task.
     *
     * @param command command that contains information for create command to execute.
     * @return String output that is printed in the GUI as Duke's response.
     */
    private String executeNewTaskCommand(ParsedInput command) {
        assert command != null : "command should not be null.";
        assert command.getTask() != null : "command Task should not be null.";
        Task newTask;
        switch (command.getCommand()) {
        case "todo":
            newTask = new ToDo(command.getTask());
            break;
        case "event":
            newTask = new Event(command.getTask(), command.getDueDate(), command.getRecurPeriod());
            break;
        case "deadline":
            newTask = new Deadline(command.getTask(), command.getDueDate(), command.getRecurPeriod());
            break;
        default:
            return outputString.showInputError();
        }

        assert newTask != null : "newTask cannot be null";
        tasks.addTask(newTask);

        if (!appendNewTaskToStorage(newTask)) {
            return outputString.showStorageError();
        }

        return outputString.sayAddTask(newTask, tasks.getTaskList().size());
    }


    /**
     * Contains and runs the main logic of Duke.
     */
    public void handleUserInput(String userInputString) {
        ParsedInput parsedUserInput = parseUserInput(userInputString);
        if (parsedUserInput == null) {
            gui.updateChatBox(outputString.showInputError());
            return;
        }

        String output = generateOutput(parsedUserInput);
        gui.updateChatBox(output);
    }

    /**
     * Adds task to storage file.
     *
     * @param taskToAppend Task that is to be added to storage.
     * @return true if appending succeeds else a false will be returned instead.
     */
    private boolean appendNewTaskToStorage(Task taskToAppend) {
        try {
            storage.appendToStorage(taskToAppend);
        } catch (DukeException errorMessage) {
            return false;
        }
        return true;
    }

    /**
     * Returns the exit status of the program
     *
     * @return true if program is set to exit else false.
     */
    public boolean getExitStatus() {
        return isExit;
    }

    /**
     * Updates storage file.
     *
     * @return true if appending update else a false will be returned instead.
     */
    private boolean updateItemsInStorage() {
        try {
            storage.updateStorageFile(tasks.getTaskList());
        } catch (DukeException errorMessage) {
            gui.updateChatBox(outputString.showStorageError());
            return false;
        }
        return true;
    }

    /**
     * Sets the interface of the GUI.
     *
     * @param stage Stage to be used for the GUI.
     */
    @Override
    public void start(Stage stage) {
        gui = Gui.createGuiForDuke(this, stage);
        stage.show();
    }

}
