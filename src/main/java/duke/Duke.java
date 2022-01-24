package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private static final String home = System.getProperty("user.home");
    // contain tasks in array
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    public static void main(String... args) {
        new Duke().run();
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;

        do {
            try {
            String request = ui.readCommand();
            Command c = Parser.parseCommand(request);
            String result = c.execute(tasks, ui, storage);
            ui.replyUser(result);
            isExit = c.isExit();
            } catch (DukeException e) {
                ui.replyUser(e.getMessage());
            }
        } while (!isExit);
    }
}


