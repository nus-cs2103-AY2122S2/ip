import command.Command;
import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/** Main class from which bot is directly run. */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /** Uses Ui, Storage and TaskList. */
    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
    }

    /**
     * Runs the bot, combining Ui, Storage, TaskList and Commands together.
     * @param args Only inputs are user inputs from Ui. Not utilised.
     * @throws DukeException If there is a problem with creating Storage file.
     */
    public static void main(String[] args) throws DukeException {
        Duke dk = new Duke();
        Ui ui = dk.ui;
        Storage storage = dk.storage;
        TaskList taskList = dk.taskList;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ui.sayHello();

        String input = ui.getCommand();
        while (!input.equals("bye")) {
            try {
                Command c = Parser.parse(input);
                c.execute(ui, storage, taskList);
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                input = ui.getCommand();
            }
        }

        ui.exit();

    }
}
