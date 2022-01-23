import java.io.IOException;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String pwd, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(pwd, filePath);
        try {
            this.tasks = new TaskList();
            storage.load();
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    public void run() {
        this.ui.showGreeting();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showEnding();
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        new Duke(home,"/data/TaskData.txt").run();
    }
}
