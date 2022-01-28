package duke;
import duke.command.InvalidCommand;
import duke.exception.DukeException;
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
        boolean run = true;
        while(run) {
            System.out.print("Me   : ");
            String message = ui.readCommand();
            ui.showLine();
            try {
                Command command = parser.parseCommand(message);
                run = command.exec(tasks, ui, storage);
            } catch (DukeException e) {
                run = new InvalidCommand(e.toString()).exec(tasks, ui, storage);
            }
        }
    }

}
