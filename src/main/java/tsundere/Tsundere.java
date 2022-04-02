package tsundere;

import command.Command;
import task.TaskList;

/**
 * A chatbot that manages tasks and stores them in data/tasks.txt.
 */
public class Tsundere {
    private static final String FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Tsundere object with a new Storage and TaskList.
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
     * Returns a string by parsing the input into command and executes the command.
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
