package jarvis;

/**
 * The Duke program implements a responsive application which keeps track of the user's to do list.
 * The persona of Duke largely follows the J.A.R.V.I.S A.I from Marvel.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class JarvisAI {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static final String DIRECTORY_PATH = "./data";
    static final String FILE_PATH = "./data/duke.txt";

    /**
     * Returns a Duke object and initializes the Ui, storage and lists of task fields with predefined paths.
     */
    public JarvisAI() {
        this.ui = new Ui();
        this.storage = new Storage(DIRECTORY_PATH, FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a Duke object and initializes the Ui, storage and lists of task fields.
     *
     * @param directoryPath path to the directory which the todo list is stored
     * @param filePath      path to the todo list file
     */
    public JarvisAI(String directoryPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(directoryPath, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     */

    public String getResponse(String input) {
        return Parser.parse(input, tasks, storage);
    }
}
