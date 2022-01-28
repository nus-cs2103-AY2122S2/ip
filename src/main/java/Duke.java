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
    public Duke(String filePath) throws Exception {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readData());
    }

    /**
     * Runs Duke program from the introduction
     * to termination.
     *
     * @throws Exception If program fails.
     */
    public void run() throws Exception {
        ui.printIntro(); // start
        Parser parser = new Parser();
        parser.processData(tasks);
        storage.writeData(tasks.getList());
        ui.printTerminate(); // end
    }

    /**
     * Main method which runs Duke program.
     * 
     * @param args The command line arguments.
     * @throws Exception If unexpected error occurs.
     */
    public static void main(String[] args) throws Exception {
        new Duke("data/duke.txt").run();
    }
}
