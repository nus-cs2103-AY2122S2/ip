package duke;

import command.Command;
import task.TaskList;

/**
 * The Duke class deals with running the bot application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UiForGUI ui;

    public Duke() {
        this("./data/duke.txt");
    }

    public Duke(String filePath) {
        this.ui = new UiForGUI();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input, this.tasks);
            c.execute(this.tasks, this.ui, this.storage);
            return this.ui.getResponse();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
