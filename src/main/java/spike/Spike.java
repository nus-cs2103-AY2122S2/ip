package spike;

import spike.command.Command;
import spike.command.FindCommand;
import spike.command.IncorrectCommand;
import spike.command.ListCommand;
import spike.exception.SpikeException;
import spike.parser.Parser;
import spike.storage.Storage;
import spike.task.TaskList;

/**
 * Starting point of the Spike chatbot program.
 * Initializes all necessary components of the application and starts to accept commands.
 */
public class Spike {
    private Storage storage;
    private TaskList tasks;

    /**
     * Default constructor for a Spike instance.
     *
     * @param directory relative path of folder where the data file sits
     * @param filePath relative path of the data file
     * @return A Spike instance with all components initialized
     */
    public Spike(String directory, String filePath) {
        this.storage = new Storage(directory, filePath);
        tasks = new TaskList();
    }

    /**
     * Gets number of tasks.
     * @return number of tasks
     */
    public int getNumOfTasks() {
        return tasks.getListSize();
    }

    /**
     * Loads file from disk.
     */
    public void loadFile() {
        try {
            tasks = new TaskList(storage.load());
        } catch (SpikeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Gets response based on user input.
     *
     * @param input input text from user
     * @return bot response text
     */
    public String getResponseCommand(String input) {
        Command command;
        command = new Parser().parseCommand(input, tasks);
        String executionResult = command.execute(tasks);
        if (!(command instanceof FindCommand || command instanceof IncorrectCommand
                || command instanceof ListCommand)) {
            saveChanges();
        }
        return executionResult;
    }

    /**
     * Saves changes made.
     */
    public void saveChanges() {
        storage.saveChanges(tasks);
    }

    /**
     * Returns the tasksList object stored
     */
    public TaskList getTasks() {
        return this.tasks;
    }
}
