package duke;

import duke.command.Command;
import duke.dukeexceptions.DukeExceptions;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Duke is a task list CLI application that stores three types of tasks, Todos, Deadlines and Events.
 * The tasks can be marked and unmarked to represent completeness.
 */
public class Duke {
    /** The Storage that acts as an interface between Duke and duke.txt */
    private Storage storage;
    /** The Task List that stores all the tasks */
    private TaskList taskList;
    /** The UI that handles user interactions */
    private Ui ui;

    /**
     * Constructs a new Duke application.
     *
     * @param filename The file which contains the list of tasks.
     */
    public Duke(String filename) {
        this.ui = new Ui();

        try {
            this.storage = new Storage(filename);
            this.taskList = storage.getData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Causes the Duke application to run.
     */
    void run() {
        this.ui.showMenu();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String userInput = sc.nextLine();
                Command cmd = this.ui.showUserCommandLine(userInput);
                cmd.run(this.taskList, this.ui, this.storage);
            } catch (DukeExceptions e) {
                ui.showCommandError(e);
            }
        }
    }

    /**
     * Executes the Duke application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("src/main/java/data/duke.txt").run();
    }
}