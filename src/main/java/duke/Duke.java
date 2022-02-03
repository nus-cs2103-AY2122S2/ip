package duke;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.io.Storage;
import duke.parser.Parser;
import duke.task.TaskStore;
import duke.ui.MainWindow;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Duke extends Application {
    private TaskStore tasks;
    private final Storage storage;
    private Ui ui;
    private final Parser parser;

    /**
     * Builds the Storage, Parser and TaskStore for Duke application
     */
    public Duke() {
        this.storage = new Storage();
        this.parser = new Parser();
        try {
            this.tasks = storage.importTasks();
        } catch (IOException e) {
            ui.printError("Unable to load file.");
        } catch (DateTimeParseException e) {
            ui.printError("Sorry I don't understand that format. Make sure its in yyyy-mm-dd.");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Builds the controllers necessary for MainWindow
            this.ui = new Ui(fxmlLoader.<MainWindow>getController().getDialogContainer());
            fxmlLoader.<MainWindow>getController().setParser(this.parser);
            fxmlLoader.<MainWindow>getController().setStorage(this.storage);
            fxmlLoader.<MainWindow>getController().setUi(this.ui);
            fxmlLoader.<MainWindow>getController().setTasks(this.tasks);

            // Builds the stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Duke");
            primaryStage.setMinHeight(600.0);
            primaryStage.setMinWidth(400.0);
            primaryStage.setResizable(false);

            // Inserts greet statement
            ui.greet();

            // Displays the stage to the user
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}