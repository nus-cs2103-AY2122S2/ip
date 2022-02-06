package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Parser;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * Main Class for Duke.
 */
public class Duke extends Application {
    private Scanner in = new Scanner(System.in);
    private Parser parser = new Parser();
    private TaskList taskList = new TaskList();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            Ui.init(fxmlLoader.<MainWindow>getController().getDialogContainer(),
                    new Image(this.getClass().getResourceAsStream("/images/jerry.png")));

            fxmlLoader.<MainWindow>getController().setParser(parser);
            fxmlLoader.<MainWindow>getController().setTaskList(taskList);

            Storage.loadFile(System.getProperty("user.dir") + "\\data\\duke.txt", taskList);

            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            Ui.print("Aye. Hi there young lad...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



