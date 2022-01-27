package duke;

import duke.exception.RonException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (RonException e) {
            this.tasks = new TaskList(filePath);
            this.ui.showLoadingError();
        }
    }

    public void run() {
        this.ui.start(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
