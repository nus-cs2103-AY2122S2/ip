package bobby;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Bobby program serves as a notepad, taking in various user
 * inputs to execute commands such as adding different types of
 * tasks, marking tasks as done and deleting tasks.
 */
public class Bobby {

    private Storage storage;
    private TaskList tasks;
    private boolean isExit;

    /**
     * Creates an instance of Bobby.
     * @param filePath location in a directory where contents will be stored.
     */
    public Bobby(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile(), storage);
        isExit = false;
    }

    /**
     * Stops the program from running.
     */
    public void terminate() {
        isExit = true;
    }

    /**
     * Runs the program and starts taking in user inputs.
     */
    public void run() {
        Scanner scannerObj = new Scanner(System.in);
        while (scannerObj.hasNextLine()) {
            String userInput = scannerObj.nextLine();
            Parser.parse(tasks, userInput, this);
            if (isExit) {
                break;
            }
        }
    }

    /**
     * Main method that uses the run method
     * @param args Unused.
     */
    public static void main(String[] args) {
        Ui.showWelcome();
        new Bobby("bobby.txt").run();
    }
}
