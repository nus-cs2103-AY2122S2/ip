package duke;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import duke.command.Parser;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Main Class for Duke.
 */
public class Duke extends Application {
    Ui ui = new Ui(null, null);
    Scanner in = new Scanner(System.in);
    Parser parser = new Parser();
    TaskList taskList = new TaskList();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            ui = new Ui(fxmlLoader.<MainWindow>getController().getDialogContainer(),
                    new Image(this.getClass().getResourceAsStream("/images/jerry.png")));
            fxmlLoader.<MainWindow>getController().setUi(ui);
            fxmlLoader.<MainWindow>getController().setParser(parser);
            fxmlLoader.<MainWindow>getController().setTaskList(taskList);
            Storage.loadFile(System.getProperty("user.dir") + "\\data\\duke.txt", taskList);
            fxmlLoader.<MainWindow>getController().setTaskList(taskList);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



