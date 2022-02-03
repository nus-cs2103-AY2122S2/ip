import duke.Parser;
import duke.Storage;
import duke.UI;

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
     * User Interface point of Duke.
     */
    private UI ui;

    /**
     * Creates Duke to process.
     *
     * @param filePath Name of the path where Duke is to be stored.
     * @throws Exception If something unexpected happens.
     */

    private Parser parser;
    String filePath = "data/duke.txt";

    public Duke() throws Exception {
        try {
            ui = new UI();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.readData());
            parser = new Parser(tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String cmd) {
        try {
            if (cmd.equals("bye")) {
                storage.writeData(tasks.getList());
                return UI.printTerminate();
            } else {
                return parser.processData(cmd);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
