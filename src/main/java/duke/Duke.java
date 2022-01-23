package duke;

/**
 * Represents the Duke application
 */
public class Duke {
    private TaskManager taskManager;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        taskManager = new TaskManager();
        try {
            storage = new Storage(filePath);
            taskManager = new TaskManager(storage.load());
        } catch (DukeException e) {
            ui.print(e.toString());
            taskManager = new TaskManager();
        }
    }

    /**
     * Runs the Duke application
     */
    public void run() {
        ui.greet();
        while (true) {
            String userInput = ui.readCommand();
            String response = Parser.parseCommand(userInput, taskManager);
            ui.print(response);
            if (Parser.isExit(userInput)) {
                try {
                    storage.save(taskManager.getTasks());
                } catch (DukeInvalidFileSaveException e) {
                    ui.print(e.toString());
                }
                return;
            }
        }
    }

    /**
     * Main driver function of the Duke application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
