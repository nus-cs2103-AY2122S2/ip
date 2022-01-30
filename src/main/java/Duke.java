import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TextUi;
import tasks.TaskList;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final TextUi ui;
    private final Parser parser = new Parser();


    /**
     * Instantiates a Duke object with a directoryPath and filePath of the storageFile
     * @param directoryPath directoryPath to storage file
     * @param filePath filePath to storage file
     */
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

    /**
     * Method that runs the Duke Program
     * @throws duke.DukeException if there is an error that occurs while parsing the input
     */
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


