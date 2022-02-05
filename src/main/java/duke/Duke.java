package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.ui.TextUi;

import javafx.application.Application;
import javafx.application.Platform;

public class Duke {
    // Global Variables
    private static final String FILENAME = "task.txt";

    private TaskList taskList;
    private Storage fh;
    private TextUi ui;
    private Parser parser;

    /**
     * The main entry point to the application
     *
     * Creates an instance of Duke and starts the application
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--cmd")) { // Command Line mode
            new Duke().run_cmd();
        } else { // GUI mode
            Application.launch(duke.ui.GraphicsUi.class, args);
        }
    }

    public Duke() {
        fh = new Storage(FILENAME);
        taskList = new TaskList(fh.importTasks());
        parser = new Parser();
        parser.setTaskList(taskList);
    }

    public String getResponse(String string) {
        Command parsedCommand = parser.parseCommand(string);
        CommandResult runCommand = parsedCommand.runCommand();

        if (ExitCommand.isExitCommand(parsedCommand)) { // Checking for exit
            Platform.exit();
        }
        return runCommand.toString();
    }

    /**
     * Starts the main logic of the program (command line mode)
     *
     * Instantiates TextUi, Storage, TaskList, and Parser objects and starts input loop
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
            // CHANGE THIS IN THE FUTURE TO GET RID OF GET FUNCTION
        }
        return false;
    }
}
