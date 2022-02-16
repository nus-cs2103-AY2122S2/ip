package bobby;

/**
 * The Bobby program serves as a notepad, taking in various user
 * inputs to execute commands such as adding different types of
 * tasks, marking tasks as done and deleting tasks.
 */
public class Bobby {

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates an instance of Bobby.
     *
     * @param filePath location in a directory where contents will be stored.
     */
    public Bobby(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.createTaskArray(), storage);
    }

    public String getResponse(String text) {
        return Parser.parse(tasks, text);
    }
}
