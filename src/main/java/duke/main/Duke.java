package duke.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * The main class for running Duke.
 * This class contains all the abstracted details for Duke.
 */
public class Duke extends Application {
    private TaskList toDoList;
    private Storage storage;
    private Ui ui;

    private final String filepath = "./tasklist.txt";

    /**
     * Constructor for Duke.
     * Tries to retrieve the list of Tasks from the default filepath
     */
    public Duke() {
        storage = new Storage(this.filepath);
        ui = new Ui();

        // Attempt to open the storage file. If file does not exist, then create a new file.
        try {
            toDoList = storage.addFileContent();
        } catch (FileNotFoundException e) {
            Ui.handleLoadError();
            // creates a new file in the current directory
            File f = new File(this.filepath);
            toDoList = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Driver for Duke.
     * Runs Duke and awaits for Commands from the user.
     *
     * @throws DukeException when a WrongCommand is given
     * @throws IOException   when an IO error occurs while reading user input
     */
    public void run() throws DukeException, IOException {
        // Declaration of variables
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd;

        // Print out the welcome message and await user input
        Ui.showWelcome();
        while (!(cmd = br.readLine()).equals("bye")) {
            String commandType = cmd.split(" ")[0];
            ui.burpReply(ui.determineType(commandType), toDoList, cmd, this.storage);
        }
        Ui.showBye();
    }

    /**
     * Main method. Used for CLI programs
     *
     * @param args unused
     * @throws DukeException when a WrongCommand is given
     * @throws IOException   when an IO error occurs
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }
}
