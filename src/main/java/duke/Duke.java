package duke;

import java.util.Scanner;

/**
 * The Duke program implements a responsive application which keeps track of the user's to do list.
 * The persona of Duke largely follows the J.A.R.V.I.S A.I from Marvel.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a Duke object and initializes the Ui, storage and lists of task fields.
     *
     * @param directoryPath path to the directory which the todo list is stored
     * @param filePath path to the todo list file
     */
    public Duke(String directoryPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(directoryPath, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Creates a new Duke object by specifying the directory and file path of the list of task then runs it.
     *
     * @param args Any arguments supplied by the user
     */
    public static void main(String[] args) {
        new Duke("./data","./data/duke.txt").run();
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        Ui.greet();
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.next();
        Parser.parse(input, sc, tasks, storage);
        Ui.bye();
        sc.close();
    }
}

