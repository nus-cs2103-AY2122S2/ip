package duke;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Ui;
import duke.command.Command;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(storage.load());
        this.parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        boolean run = true;
        while(run) {
            System.out.print("Me   : ");
            String message = ui.readCommand();
            ui.showLine();
            Command command = parser.parseCommand(message);
            run = command.exec(tasks, ui, storage);
        }
    }

}
