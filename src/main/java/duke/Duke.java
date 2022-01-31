package duke;

import duke.command.Command;
import duke.dukeexceptions.DukeExceptions;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

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

    public static void main(String[] args) {
        // Creates a new duke application and tells it to run.
        new Duke("data/duke.txt").run();
    }
}