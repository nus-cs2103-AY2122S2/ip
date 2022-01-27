package duke;

/**
 * The main class of the program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of the class.
     * @param filePath The filepath of the stored data.
     */
    Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage);
            ui = new Ui(tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.run();
    }

    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
