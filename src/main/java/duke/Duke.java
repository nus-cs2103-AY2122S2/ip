package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke runs a chatbot that saves and updates tasks input by the user.
 *
 * @author Chan Yi Juan
 * @version 0.1
 * @since 2022-01-10
 */
public class Duke extends Application {
    /**
     * Encapsulates Duke's UI interaction.
     */
    private Ui ui;
    /**
     * Encapsulates a list of tasks.
     */
    private TaskList<Task> tasks;
    /**
     * Encapsulates Duke's storage logic
     */
    private Storage storage;

    /**
     * Instantiates a new Duke.
     *
     * @throws IOException the io exception
     */
    public Duke() throws IOException {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = this.storage.loadSavedTasks();
    }

    /**
     * Runs the Duke chatbot program.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean exitDuke = false;

        while (!exitDuke) {
            try {
                String fullCommand = ui.readUserInput();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                exitDuke = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    ///**
    // * The entry point of the Duke application.
    // *
    // * @param args the input arguments
    // * @throws IOException when storage file does not exist
    // */
    //public static void main(String[] args) throws IOException {
    //    new Duke().run();
    //}
}
