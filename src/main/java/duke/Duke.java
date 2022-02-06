package duke;

import javafx.application.Application;

/**
 * Represents an instance of the <code>Duke</code> chatbot, which
 * can then be run.
 */


public class Duke {
    protected static Storage storage;
    protected static TaskList tasks;

    public Duke(String filepath) {
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        Duke duke = new Duke("../../../data/duke.txt");
        assert !(duke == null) : "Duke should not be null";
        Application.launch(Ui.class, args);
        Duke.storage.save(Duke.tasks);

    }
}
