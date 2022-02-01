package duke;
import tasks.TaskList;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final TextUi ui;
    private final Parser parser = new Parser();


    /**
     * Instantiates a duke.Duke object with a directoryPath and filePath of the storageFile
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
     * Method that runs the duke.Duke Program
     */
    public String getResponse(String input) {
        try {
            return parser.parseInput(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}


