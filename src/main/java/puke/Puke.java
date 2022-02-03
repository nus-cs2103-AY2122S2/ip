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

    public void loadTasksFromFile() {
        try {
            storage.loadTasks(tasks);
        } catch (PukeException e) {
            return;
        }
    }

    public void saveTasksToFile() {
        try {
            storage.saveTasks(tasks);
        } catch (PukeException e) {
            return;
        }
    }

    /**
     * Gets the response of the chatbot for the user input.
     *
     * @param input User input.
     * @return Response from the chatbot.
     */
    public String getResponse(String input) {
        String response = "";

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
