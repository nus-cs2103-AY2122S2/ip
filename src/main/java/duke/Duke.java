package duke;

import java.io.IOException;

import duke.Ui.MainWindow;
import duke.Ui.Ui;
import duke.command.Command;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the Duke Application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class Duke extends Application {

    private static TaskList taskList;
    private static Storage storage;
    private static final Parser parser = new Parser();
    private static Ui ui;

    /**
     * Initialization of Duke application.
     */
    public Duke() {
        storage = new Storage(parser);
        try {
            taskList = storage.importTasks();
        } catch (IOException e) {
            ui.print(Ui.MSG_FILEREADERROR);
        }
    }

    public String getResponse(String inputTxt) {
        try {
            Command c = parser.parse(inputTxt);
            return c.execute(taskList, storage).trim();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return Ui.MSG_FILEWRITEERROR;
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            ui = new Ui(fxmlLoader.<MainWindow>getController().getDialogContainer());

            stage.setScene(scene);
            stage.setTitle("DatoDato");
            stage.setResizable(false);

            fxmlLoader.<MainWindow>getController().setDuke(this);

            ui.welcomeMsg();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
