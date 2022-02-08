package duke;

import task.TaskList;

/**
 * Representation of Duke.
 */
public class Duke {

    /**
     * File where Duke is stored, read and written.
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
            storage = new Storage(FILE_PATH);
            tasks = new TaskList(storage.readData());
            parser = new Parser(tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns Duke response according to
     * the user command.
     *
     * @param cmd User command.
     * @return Duke response.
     */
    public String getResponse(String cmd) {
        try {
            if (cmd.equals("bye")) {
                storage.writeData(tasks.getList());
                return UI.printTerminate();
            } else {
                return parser.processCommand(cmd);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
