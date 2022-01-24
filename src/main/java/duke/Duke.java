package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    // contain tasks in array
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage("/ip/data");
        tasks = new TaskList(storage.load());
    }

    public static void main(String... args) {
        new Duke().run();
    }

    public void run() {
        ui.greetUser();
        boolean isExit;

        do {
            String request = ui.readCommand();
            Command c = new Parser().parseCommand(request);
            String result = c.execute(tasks, ui, storage);
            ui.replyUser(result);
            isExit = c.isExit();
        } while (!isExit);
    }
}


