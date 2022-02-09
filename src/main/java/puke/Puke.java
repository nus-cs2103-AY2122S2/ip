package puke;

import puke.exception.PukeException;
import puke.io.Storage;
import puke.parser.Parser;
import puke.task.TaskList;

/**
 * Main class for Puke.
 */
public class Puke {
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;

    /**
     * Initialises Puke and the objects required.
     *
     * @param filePath Path of storage file.
     */
    public Puke(String filePath) {
        tasks = new TaskList();
        storage = new Storage(filePath);
        parser = new Parser();
    }

    /**
     * Loads tasks from the storage file into the task list.
     *
     * @throws PukeException If the tasks cannot be loaded.
     */
    public void loadTasksFromFile() throws PukeException {
        storage.loadTasks(tasks);
    }

    /**
     * Saves tasks to the storage file from the task list.
     *
     * @throws PukeException If the tasks cannot be saved.
     */
    public void saveTasksToFile() throws PukeException {
        storage.saveTasks(tasks);
    }

    /**
     * Gets the response of the chatbot for the user input.
     *
     * @param input User input.
     * @return Response from the chatbot.
     */
    public String getResponse(String input) {
        String response;

        try {
            response = parser.processInput(input, tasks);

            if (response == null) {
                saveTasksToFile();
            }

            return response;
        } catch (PukeException e) {
            return e.getMessage();
        }
    }
}
