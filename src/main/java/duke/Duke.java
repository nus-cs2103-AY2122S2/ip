package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.ui.TextUi;

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
        new Duke().run();
    }

    /**
     * Starts the main logic of the program
     *
     * Instantiates TextUi, Storage, TaskList, and Parser objects and starts input loop
     */
    public void run() {
        // Setting up the goods
        ui = new TextUi();
        fh = new Storage(FILENAME);
        taskList = new TaskList(fh.importTasks());
        parser = new Parser();
        parser.setTaskList(taskList);

        ui.printIntro();

        // Starting input loop
        while (true) {
            String input = ui.getInput();
            if (processInput(input)) {
                break;
            }
        }
        ui.printGoodbye();
    }

    private boolean processInput(String input) {
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