package duke;

import java.io.IOException;

/**
 * Duke class which acts as a chatbot which can save tasks that users input.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    /**
     * Constructs a Duke class that runs the chatbot.
     *
     */
    public Duke() {
        String home = System.getProperty("user.home");
        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "duke.txt");
        ui = new Ui();
        storage = new Storage(String.valueOf(filePath));
        storage.makeDirectory();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        parser = new Parser(ui, tasks, storage);
    }

    public Parser getParser() {
        return parser;
    }

    public Ui getUi() {
        return ui;
    }
}
