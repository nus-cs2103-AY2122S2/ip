package cleese;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import exceptions.NoDescException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import task.TaskList;
import ui.MainWindow;
import ui.Ui;

public class Cleese extends Application {
    private static Ui ui;
    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;

    /**
     * Initializes the elements needed for the Cleese class to work correctly
     */
    public static void initialize() {
        String filePath = "./TaskListDB.txt";
        File file = new File(filePath);
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        taskList = new TaskList();
        // Read inputs from file
        try {
            storage.readFromFile(taskList);
            file.createNewFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception");
        } catch (IOException e) {
            System.out.println("Data file cannot be created");
        }
    }

    public String getResponse(String input) {
        String response;
        try {
            response = parser.handleCommand(input, taskList, ui, storage);
        } catch (NoDescException error) {
            response = "I'm sorry sir, The description of a todo cannot be empty.";
        } catch (Exception error) {
            response = "I'm sorry sir, but I don't quite understand";
        }
        assert response.getClass() == String.class;
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
