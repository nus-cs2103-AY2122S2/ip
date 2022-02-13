package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.misc.Pair;
import duke.taskobjects.Task;
import duke.ui.TextUi;
import javafx.application.Application;
import javafx.application.Platform;

public class Duke {
    private static final String FILENAME = "task.txt";
    private static final String WELCOME_MESSAGE = "Welcome to your Task List Assistant!\n\n";

    private final TaskList taskList;
    private final Storage fh;
    private TextUi ui;
    private final Parser parser;
    private final boolean fileExistsAtStart;

    /**
     * Creates a new Duke object.
     * Initializes the main variables and other objects.
     */
    public Duke() {
        fh = new Storage(FILENAME);
        Pair<Boolean, ArrayList<Task>> loadTaskListFileResults = fh.loadTaskListFile();
        fileExistsAtStart = loadTaskListFileResults.first();
        taskList = new TaskList(loadTaskListFileResults.second());
        parser = new Parser();
        parser.setTaskList(taskList);
    }

    /**
     * Creates an instance of Duke and starts the application.
     * The main entry point to the application.
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--cmd")) { // Command Line mode
            new Duke().run_cmd();
        } else { // GUI mode
            Application.launch(duke.ui.GraphicsUi.class, args);
        }
    }

    /**
     * Returns a welcome message which differs depending on the task list file.
     *
     * @return A welcome message.
     */
    public String getWelcomeMessage() {
        if (fileExistsAtStart) {
            return WELCOME_MESSAGE
                    + "Existing Task List file loaded...\n" + taskList.listAll();
        }
        return WELCOME_MESSAGE + "Task List file not found... starting fresh.";
    }

    /**
     * Sends user input to Parser and returns the result to the GUI.
     *
     * @param string User input.
     * @return Results of the Command.
     */
    public CommandResult getResponse(String string) {
        Command parsedCommand = parser.parseCommand(string);
        CommandResult runCommand = parsedCommand.runCommand();

        if (ExitCommand.isExitCommand(parsedCommand)) { // Checking for exit
            Platform.exit();
        }

        // Flush to disk if modified
        if (runCommand.isModified()) {
            fh.exportTasks(taskList.getList());
        }

        return runCommand;
    }

    /**
     * Starts the main logic of the program (command line mode).
     * Instantiates TextUi, Storage, TaskList, and Parser objects and starts input loop.
     */
    public void run_cmd() {
        // Setting up the goods
        ui = new TextUi();
        ui.printIntro();

        // Starting input loop
        while (true) {
            String input = ui.getInput();
            if (processInput_cmd(input)) {
                break;
            }
        }
        ui.printGoodbye();
    }

    private boolean processInput_cmd(String input) {
        Command parsedCommand = parser.parseCommand(input);
        CommandResult runCommand = parsedCommand.runCommand();

        if (ExitCommand.isExitCommand(parsedCommand)) { // Checking for exit
            return true;
        }
        // Print results
        ui.printResults(runCommand);

        // Flush to disk if modified
        if (runCommand.isModified()) {
            fh.exportTasks(taskList.getList());
        }
        return false;
    }
}
