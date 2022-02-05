package duke;
import java.util.Scanner;
/**
 * Main driver class for Duke.
 */
public class Duke {
    private final Ui ui;
    private Storage storage;
    private TaskList tasks;


    /**
     * Constructor of Duke, creating a new Duke.
     * Setup Ui, Storage and TaskList.
     * @param filePath filePath of Storage of TaskList
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getTaskList());
    }


    /**
     * Main Driver Method to run program until exit command is called.
     */
    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().trim(); // Can also convert result to lower-case to handle cases.
            Parser parser = new Parser(storage, tasks, ui);
            parser.parse(command);
            boolean isExitTriggered = parser.isExitTrigger();
            if (isExitTriggered) {
                isExit = true;
            }
        }
    }

    /**
     * Main method to start Duke.
     *
     * @param args Command Line Argument not used in this iteration of the program.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}