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
        storage.loadTo(taskList);
        parser = new Parser(taskList, storage);
    }

    /**
     * Gets response to the given user input.
     *
     * @param input user input.
     * @return response message to the given user input.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }
}
