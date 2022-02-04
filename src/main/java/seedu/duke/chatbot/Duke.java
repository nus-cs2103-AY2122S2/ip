package seedu.duke.chatbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.duke.command.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.gui.MainWindow;
import seedu.duke.task.TaskList;

import java.io.IOException;

/**
 * Functions as the chatbot by taking in inputs.
 * Also helps in giving out specified outputs.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke() {
        //for JavaFx to run
    }
    /**
     * Creates the chatbot.
     * @param filePath which provides the path to the database, a txt file storing old tasks
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, ui);
        try {
            this.taskList = storage.getOldTaskList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        //might cause bug, untested
        String name  = ui.showWelcome();
        Parser parser = new Parser(name);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            String filePath = System.getProperty("user.dir")  + "/data/OldTasks.txt";
            Duke duke = new Duke(filePath);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = parser.parse(input);
            this.taskList = c.execute(this.taskList, this.ui, this.storage);
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
        }
        return response;
    }

    /*
    public static void main(String[] args) {
    Runs the chatbot Duke with a specified path to the database file.
        String filePath = System.getProperty("user.dir")  + "/data/OldTasks.txt";
        new Duke(filePath).run();
    }
    */

    /*
    public void run() {
        Starts the chatbot by taking in commands and executing them.
        String name  = ui.showWelcome();
        Parser parser = new Parser(name);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand); //read the full command and return the command
                this.taskList = c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    */


}