package cleese;

import task.TaskList;
import exceptions.NoDescException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;
import ui.Ui;

public class Cleese extends Application {
    private static Ui ui;
    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;

    public static void initialize() {
        String filePath = "./src/TaskListDB.txt";
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        taskList = new TaskList();
        // Read inputs from file
        try {
            storage.readFromFile(taskList);
        } catch (FileNotFoundException e) {
            File fileName = new File("./src/TaskListDB.txt");
        }
    }

    public String getResponse(String input) {
        String response;
        try {
            response = parser.handleCommand(input, taskList, ui, storage);
        } catch (NoDescException error) {
            response = "OOPS!!! The description of a todo cannot be empty.";
        } catch (Exception error) {
            response = "OOPS!!! I'm sorry but I don't know what that means :-(";
        }
        return response;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Cleese.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}