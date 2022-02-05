package ann;

import ann.commands.Command;
import ann.data.TaskList;
import ann.parser.Parser;
import ann.storage.Storage;

/**
 * Represents the main component of the program which controls the other components.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class Ann {
    /**
     * Represents the storage component of the program.
     */
    private Storage storage;
    /**
     * Represents the current task list.
     */
    private TaskList tasks;

    /**
     * Creates a new Ann object.
     *
     * @throws RuntimeException if the storage cannot be initialized.
     */
    public Ann() {
    }

    /**
     * Starts the program by initializing the storage and task list.
     */
    public void init() {
        try {
            this.storage = new Storage();
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getResponse(String text) {
        Command command = Parser.parse(text);
        executeCommand(command);
        return command.getMessage();
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
            throw new RuntimeException(e);
        }
    }
}
