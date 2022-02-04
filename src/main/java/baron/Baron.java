package baron;

import java.util.Scanner;

import baron.commands.Command;
import baron.commands.CommandManager;
import baron.exceptions.BaronException;
import baron.tasks.TaskManager;
import baron.util.Storage;
import baron.util.TextUi;

/**
 * Main class for the Baron application that user uses to run. The Baron application keeps and
 * tracks tasks like a to-do list.
 */
public class Baron {
    private static final String DEFAULT_STORAGE_FILE_PATH = "data/baron.txt";

    private final Scanner inputScanner;
    private final TaskManager taskManager;
    private final CommandManager commandManager;
    private final Storage storage;
    private final TextUi textUi;

    public Baron() {
        this(Baron.DEFAULT_STORAGE_FILE_PATH);
    }

    /**
     * Constructs a {@code Baron} object with the specified relative file path.
     *
     * @param relativeFilePath the relative file path to be used for Storage.
     */
    public Baron(String relativeFilePath) {
        TaskManager taskManagerTemp;
        this.inputScanner = new Scanner(System.in);
        this.storage = new Storage(relativeFilePath);
        try {
            taskManagerTemp = new TaskManager(this.storage.load());
        } catch (BaronException e) {
            TextUi.printCommandOutput(e.toString());
            taskManagerTemp = new TaskManager();
        }
        this.taskManager = taskManagerTemp;
        this.commandManager = new CommandManager(this.taskManager, this.storage);
        this.textUi = new TextUi(this.taskManager);
    }

    /**
     * Starts the Baron application.
     */
    private void start() {
        this.textUi.showWelcomeMessage();
        Command command;

        do {
            String fullCommand = inputScanner.nextLine();
            command = this.commandManager.parseCommand(fullCommand);
            TextUi.printCommandOutput(command.execute());
        }
        while (!command.isByeCommand());

        try {
            this.storage.save(this.taskManager.getAllTasks());
        } catch (BaronException e) {
            TextUi.printCommandOutput(e.toString());
        }
    }

    /**
     * Returns the response of the specified user input.
     *
     * @param input the input specified to get a response.
     * @return the response of the specified user input.
     */
    public String getResponse(String input) {
        return this.commandManager.parseCommand(input).execute();
    }

    /**
     * Initialises and starts the Baron application.
     *
     * @param args the command line arguments (not used).
     */
    public static void main(String[] args) {
        new Baron("data/baron.txt").start();
    }
}
