package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Ace (Duke), a program that manages tasks and todos for the user.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Duke task manager.
     *
     * @param filepath The filepath in which the task list is stored and retrieved from.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Begins running Duke.
     */
    public void run() {
        boolean exit = false;
        ui.displayWelcome();

        while (!exit) {
            try {
                String strCommand = ui.readCommand();
                Command command = Parser.parse(strCommand);
                command.execute(taskList, ui, storage);
                exit = command.isExit();
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
