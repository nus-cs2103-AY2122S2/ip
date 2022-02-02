import Commands.DukeCommand;
import Exceptions.DukeException;
import Tasks.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

import java.io.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for the Duke class
     * @param filePath The path of the file which acts as the databse
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e.getMessage());
        }

    }

    /**
     * Runs the application
     */
    /*
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();

                DukeCommand c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }
     */

    public String executeCommand(String input) throws IOException, DukeException {
        DukeCommand c = Parser.parse(input);
        return c.execute(tasks, ui, storage);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
