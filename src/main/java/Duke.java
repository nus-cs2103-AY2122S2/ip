import Task.TaskList;
import Duke.*;

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
        tasks = new TaskList(storage.read());
    }

    /**
     * Runs Duke program from the introduction
     * to termination.
     *
     * @throws Exception If program fails.
     */
    public void run() throws Exception {
        ui.intro(); // start
        Parser p = new Parser();
        p.process(tasks);
        storage.write(tasks.getList());
        ui.terminate(); // end
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
