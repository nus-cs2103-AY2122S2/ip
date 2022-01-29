package ann;

import ann.commands.Command;
import ann.commands.ExitCommand;
import ann.data.TaskList;
import ann.parser.Parser;
import ann.storage.Storage;
import ann.ui.Ui;

/**
 * Represents the main component of the program which controls the other components.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class Ann {
    /** Represents the storage component of the program.*/
    private Storage storage;
    /** Represents the current task list.*/
    private TaskList tasks;
    /** Represents the user interface component of the program.*/
    private Ui ui;

    /**
     * Creates a new Ann object.
     *
     * @throws RuntimeException if the storage cannot be initialized.
     */
    public Ann() {
    }

    /**
     * Drives the program.
     * 
     * @param args the initial input (folder and file name for storage).
     */
    private void run(String[] args) {
        start(args);
        runCommandUntilExitCommand();
        exit();
    }

    /**
     * Starts the program by initializing the user interface, storage and task list.
     * 
     * @param args the initial input (folder and file name for storage).
     */
    public void start(String[] args) {
        try {
            this.ui = new Ui();
            this.storage = initializeStorage(args);
            this.tasks = new TaskList(storage.load());
            ui.showGreeting();
        } catch (Exception e) {
            ui.showFailedInitMessage();
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a Storage object with the given folder and file names.
     *
     * @param args the initial input (folder and file name for storage).
     * @return a Storage object with the given folder and file names.
     * @throws Storage.StorageOperationException if the storage cannot be initialized.
     */
    private Storage initializeStorage(String[] args) throws Storage.StorageOperationException {
        return args.length != 2 ? new Storage(args[0], args[1]) : new Storage();
    }

    /**
     * Requests for user input continuously until the user inputs 'exit'.
     */
    private void runCommandUntilExitCommand() {
        Command command;
        do {
            command = Parser.parse(ui.getCommand());
            String commandMessage = executeCommand(command);
            ui.showToUser(commandMessage);
        } while(!ExitCommand.isExit(command));
    }

    /**
     * Executes the given Command, saves the updated TaskList to storage
     * and returns a String which is the message of the Command.
     *
     * @param command a Command.
     * @return a String which is the message of the Command.
     * @throws RuntimeException if unable to save to storage.
     */
    private String executeCommand(Command command) {
        try {
            command.setTaskList(tasks);
            command.executeCommand();
            storage.save(tasks);
            return command.getMessage();
        } catch (Storage.StorageOperationException e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Shows the exit message before exiting from the program.
     */
    private void exit() {
        ui.showExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Ann().run(args);
    }
}