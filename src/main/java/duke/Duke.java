package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * duke.Duke Class contains a scanner to read user input and a duke.task.TaskList that contains all tasks.
 * duke.Duke only handles the processing of user input and responding to the user. duke.task.Task list logic is
 * handled by the duke.task.TaskList class.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

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