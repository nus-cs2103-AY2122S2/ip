package duke;

/**
 * Main driver class for Duke.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke() {
    }

    /**
     * Constructor of Duke, creating a new Duke.
     * Setup Ui, Storage and TaskList.
     *
     * @param filePath filePath of Storage of TaskList
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getTaskList());
        ui = new Ui();
        parser = new Parser(storage, tasks, ui);
    }


    /**
     * Main Driver Method to run program until exit command is called.
     */
    public void run() {
        ui.start();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        Parser parser = new Parser(storage, tasks, ui);
        return parser.parse(input);
    }

    /**
     * Main method to start Duke.
     *
     * @param args Command Line Argument not used in this iteration of the program.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}