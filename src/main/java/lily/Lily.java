package lily;

import lily.control.MainWindow;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Main class which runs an interactive CLI-based chatbot which manages your todos
 * A GUI using FXML
 * 
 * @author Hong Yi En, Ian
 * @version Feb 2022 (AY21/22 Sem 2)
 */
public class Lily extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private final String FILEPATH = "./data/tasks.txt";

    /**
     * Creates a new Lily object for the GUI to run the application.
     */
    public Lily() {
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
            ui = new Ui(true);
        } catch (LilyException e) {
            tasks = new TaskList();
            ui = new Ui(false);
        }
        parser = new Parser(tasks, ui, storage);
    }


    /**
     * Previous driver method of the application before GUI introduced.
     * Used for unit tests only
     * 
     * @param args Contents of the main driver.
     */
    public static void main(String[] args) {
        new Lily().run();
    }

    /**
     * Runs the application.
     */
    public void run() {
        assert tasks != null : "Tasks should have been created in the constructor.";
        ui.showWelcome(tasks);
    }

    /**
     * Launcher calls this method to start the GUI
     * Can we extract this as a separate class?
     *
     * @param stage is the window that is being displayed
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Lily.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLily(new Lily());

            // greet the user and list their task list if they have one saved.
            // MainWindow.display(ui.showWelcome(tasks));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Accepts a command from the text box and passes it to the parser.
     *
     * @param s User's sentence in the text box.
     */
    @FXML
    public void readCommand(String s) {
        this.parser.readCommand(s);
    }

    /**
     * Gives Lily's response after processing.
     *
     * @return a string of Lily's response.
     */
    @FXML
    public String getResponse() {
        return this.ui.getOutput();
    }

    /**
     * Shows the error to the GUI
     */
    @FXML
    public void displayError(String msg) {
        this.ui.showError(msg);
    }

    /**
     * Quits the application
     */
    @FXML
    public static void exitApplication() {
        Platform.exit();
    }
}