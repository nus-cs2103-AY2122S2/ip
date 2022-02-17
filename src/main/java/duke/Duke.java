package duke;

import task.TaskList;

/**
 * Representation of Duke.
 */
public class Duke {

    /**
     * Storage where Duke is saved, read and updated.
     */
    private Storage storage;
    /**
     * List containing Tasks for Duke to process.
     */
    private TaskList tasks;
    /**
     * Parser to handle user inputs.
     */
    private Parser parser;
    /**
     * Name of file path to store data.
     */
    protected final String FILE_PATH = "data/duke.txt";

    /**
     * Creates Duke.
     */
    public Duke() {
        try {
            storage = new Storage(this.FILE_PATH);
            tasks = new TaskList(this.storage.readData());
            parser = new Parser(this.tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns Duke response according to
     * the user command.
     *
     * @param command User command.
     * @return Duke response.
     */
    public String getResponse(String command) {
        try {
            if (command.equals("bye")) {
                storage.writeData(tasks.getList());
                return UI.printTerminate();
            } else {
                return parser.processCommand(command);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
