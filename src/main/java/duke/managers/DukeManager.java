package duke.managers;

import duke.exceptions.DukeException;
import duke.commands.Command;
import duke.tasks.TaskList;

/**
 * Represents the manager of the entire Duke program.
 * Stores all the required instances of the program which includes
 * the storage, task list and ui.
 */
public class DukeManager {

    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    /**
     * Creates an instance of DukeManager.
     *
     * @param storagePath the relative path to the storage file
     *                    used for the program.
     */
    public DukeManager(String storagePath) {
        storage = new Storage(storagePath);
        ui = new Ui();

        try {
            taskList = storage.load();
        } catch (DukeException e) {
            taskList = new TaskList();
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Executes the program loop of Duke.
     */
    public void run() {
        ui.greet();
        boolean exited = false;
        Parser parser = new Parser();
        while (!exited) {
            try {
                String input = ui.getInput();
                ui.printLine();
                Command command = parser.parse(input);
                command.execute(taskList, ui, storage);
                exited = command.getExit();
            } catch (DukeException exception) {
                ui.showMessage(exception.getMessage());
            } finally {
                ui.printLine();
            }
        }
        ui.close();
    }


}
