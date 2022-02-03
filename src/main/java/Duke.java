import java.util.Scanner;

import component.Storage;
import component.TaskList;
import component.Ui;

/**
 *  A class that encapsulates the logic of Duke - A todo list program
 *  that helps user keep track of tasks.
 */
public class Duke {

    /**
     * {@link Storage}
     */
    private final Storage storage;
    /**
     * {@link TaskList}
     */
    private final TaskList tasks;

    /**
     * {@link Ui}
     */
    private final Ui ui;

    /**
     * Constructor for Duke
     * @param filePath Relative file path(from project root) to access saved tasks
     *                 from previous instance.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Run Duke program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.run(sc, tasks, storage);
    }

    /**
     * Driver method for duke.
     * @param args CLI input for Main.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
