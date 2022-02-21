package kenobi;

import kenobi.command.Command;
import kenobi.command.ExitCommand;
import kenobi.parser.ParseException;
import kenobi.parser.Parser;
import kenobi.storage.LoadStorageException;
import kenobi.storage.Storage;
import kenobi.task.TaskList;

/**ip [run]
 * The Kenobi program implements a chatbot that manages a list of tasks.
 */
public class Kenobi {
    private static final String GREETING_MSG = "Hello there, my name is Kenobi. How may I serve you?";

    private TaskList tasks;
    private final Storage storage;

    private String response;
    private boolean isTerminated;

    /**
     * Constructs an instance of Kenobi with the specified save path for storage.
     *
     * @param savePath The path that directs to the storage that Kenobi uses.
     */
    public Kenobi(String savePath) {
        storage = new Storage(savePath);
    }

    /**
     * Initializes the instance of Kenobi.
     */
    public void init() {
        try {
            tasks = storage.load();
            response = GREETING_MSG;
        } catch (LoadStorageException e) {
            response = e.getMessage();
            tasks = new TaskList();
        }
    }

    /**
     *
     */
    public String greet() {
        return GREETING_MSG;
    }

    /**
     * Executes the command input.
     * The commands will first be parsed by Parser then executed.
     */
    public void executeCommand(String input) {
        try {
            Command cmd = Parser.parseCommand(input);
            cmd.setData(tasks, storage);
            response = cmd.execute();

            if (cmd instanceof ExitCommand) {
                isTerminated = true;
            }
        } catch (ParseException e) {
            response = e.getMessage();
        }
    }

    public String getResponse() {
        return response;
    }

    public boolean isTerminated() {
        return isTerminated;
    }
}

