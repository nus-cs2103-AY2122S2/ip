package duke;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Ui;

import duke.command.Command;
import duke.command.InvalidCommand;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public static void main(String[] args) {
        Duke chatBot = new Duke("data/data.txt");
        chatBot.run();
    }

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(storage.load());
        this.parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        boolean shouldRun = true;
        while(shouldRun) {
            System.out.print("Me   : ");
            String message = ui.readCommand();
            ui.showLine();
            try {
                Command command = parser.parseCommand(message);
                shouldRun = command.exec(tasks, ui, storage);
            } catch (DukeException e) {
                shouldRun = new InvalidCommand(e.toString()).exec(tasks, ui, storage);
            }
        }
    }

}
