package duke.ui;
import duke.command.Command;

/**
 * Previously responsible for running the chatbot program,
 * now simply a container of Storage, TaskList and Ui variables.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.load();
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the storage
     * @return storage
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Returns taskList.
     * @return taskList
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Returns Ui.
     * @return Ui
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Executes the Duke ChatBot App
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(); //this line replaced by GUI text input
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Runs the program. Make sure
     * the specified file location is correct.
     * @param args Some string arguments.
     */
    public static void main(String[] args) {
        new Duke("C:/repos/ip/data/tasks.txt").run();
    }
}
