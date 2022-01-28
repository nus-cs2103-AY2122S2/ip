import java.io.IOException;

/**
 * The driver class that runs the program.
 */
public class Duke {
    private final Ui ui;
    private final Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor to initialize an instance of Duke class with folder
     * name and file name.
     *
     * @param folderName Folder name of the data file
     * @param fileName File name of the data file
     */
    public Duke(String folderName, String fileName) {
        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage(folderName, fileName);
            taskList = storage.loadTasksFromFile();
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Executes the program.
     */
    public void run() {
        ui.displayWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandLine = ui.readCommand();
                Command command = parser.parse(commandLine);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    /**
     * Starts the execution of the program.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("data", "tasks.txt").run();
    }
}
