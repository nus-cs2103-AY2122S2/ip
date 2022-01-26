import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;

    public Duke(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.isEmpty()) {
                    break;
                }
                Command<String> c = Parser.parseInput(fullCommand, tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.err.print(Ui.lineDivider + e + Ui.lineDivider);
            }
        }
        Ui.showGoodBye();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
