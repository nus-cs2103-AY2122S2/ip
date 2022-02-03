package tsundere;

import command.Command;
import task.TaskList;

/**
 * A chatbot that manages tasks and stores them in data/tasks.txt
 */
public class Tsundere {
    private Storage storage;
    private TaskList tasks;
    private static String FILE_PATH = "data/tasks.txt";

    /**
     * A chatbot that manages tasks and stores them in data/tasks.txt
     */
    public Tsundere() {
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (TsundereException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (TsundereException e) {
            return e.getMessage();
        }
    }


}
