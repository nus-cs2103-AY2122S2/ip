package duke.managers;

import duke.exceptions.DukeException;
import duke.commands.Command;
import duke.tasks.TaskList;

public class DukeManager {

    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

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
                exited = command.isExit();
                ui.printLine();
            } catch (DukeException exception) {
                ui.showMessage(exception.getMessage());
            }
        }
        ui.close();
    }


}
