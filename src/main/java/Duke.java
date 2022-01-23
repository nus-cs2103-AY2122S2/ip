import duke.*;
import tasks.*;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final TextUi ui;
    private final Parser parser = new Parser();


    public Duke(String directoryPath, String filePath) {
        TaskList tasks1;
        this.ui = new TextUi();
        this.storage = new Storage(directoryPath, filePath);
        try {
            tasks1 = new TaskList(storage.readFromDukeFile());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            tasks1 = new TaskList();
        }
        this.tasks = tasks1;
    }

    public void run() throws DukeException {
        ui.greeting();
        boolean isExit = false;

        while (!isExit) {
            isExit = parser.parseInput(ui.readCommand());
            ui.showDivider();
        }

        ui.sayBye();
        ui.closeScanner();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("./data", "./data/duke.txt").run();
    }

}


