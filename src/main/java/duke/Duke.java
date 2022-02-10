package duke;

/**
 * Represents a Duke chat bot and task management system.
 */
public class Duke {
    private static final String PATH = "./duke.txt";

    private final Parser parser;

    /**
     * Class constructor.
     */
    public Duke() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage(PATH);
        try {
            storage.loadTo(taskList);
        } catch (DukeException e) {
            System.err.println(e.toString());
        }
        parser = new Parser(taskList, storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }
}








