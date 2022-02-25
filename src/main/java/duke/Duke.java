package duke;

/**
 * Main driver class for Duke.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

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
     * Method to get response from User input
     *
     * @param input Input for parser
     * @return Response to user input
     */
    protected String getResponse(String input) {
        String result = parser.parse(input);
        assert !result.isEmpty() : "Input cannot be empty!!";
        return parser.parse(input);
    }

    /**
     * Main method to start Duke.
     *
     * @param args Command Line Argument not used in this iteration of the program.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt");
    }
}