package duke;

import duke.command.Command;
import duke.dukeexceptions.DukeExceptions;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
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
        // Creates the new UI for Duke.
        ui = new Ui();

        // Gets the data from filename and creates a new task list based on the data in filename.
        try {
            storage = new Storage(filename);
            taskList = storage.getData();
        } catch (IOException e) {
            // If file could not be found, create new file name and get fata from there.
            File newFile = new File("data/");
            if (!newFile.exists()) {
                newFile.mkdir();
            }
            newFile = new File(filename);
            try {
                newFile.createNewFile();
                storage = new Storage(filename);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Causes the Duke application to run.
     */
    void run() {
        // Creates a new scanner input for duke for user input.
        Scanner sc = new Scanner(System.in);

        // Open the start menu for duke.
        ui.showMenu();

        // The main operartion for duke. Keeps on running until the user enters bye.
        while (true) {
            try {
                // Waits for the user input, then run the command.
                String userInput = sc.nextLine();
                Command cmd = ui.showUserCommandLine(userInput);
                cmd.run(taskList, ui, storage);
            } catch (DukeExceptions e) {
                // Shows the custom duke expression message.
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
        // Creates a new duke application and tells it to run.
        new Duke("data/duke.txt").run();
    }
}