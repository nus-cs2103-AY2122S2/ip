package duke;

import command.Command;
import task.TaskList;

/**
 * The Duke class deals with running the bot application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
     * Runs the bot application until the user inputs the exit command.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isRun = true;
        while (isRun) {
            try {
                String command = this.ui.readCommand();
                Command c = Parser.parse(command, this.tasks);
                c.execute(this.tasks, this.ui, this.storage);
                isRun = c.isRunProgram();
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
            }
        }
    }
}
