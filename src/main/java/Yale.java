import yale.command.Parser;
import yale.command.Storage;
import yale.command.Ui;
import yale.task.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * Represents a chatbot that tracks
 * tasks for users.
 */
public class Yale extends Application {
    /**
     * Creates Ui, Storage, Parser
     * Scanner and TaskList objects.
     */

    private static final String FILE_PATH = "data/yale.txt";
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList list;

    /**
     * Constructor method
     */
    public Yale() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        parser = new Parser();
        list = new TaskList();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Starts the chatbot program.
     */
    public void run() {
        ui.welcomePrompt();
        Scanner scanner = new Scanner(System.in);
        String fileData = storage.loadFileContents();
        list.importIn(fileData);

        while (true) {
            String command = ui.receiveInput(scanner);
            parser.performAction(command, list);
            storage.writeTextTo(list);
            if (ui.checkExit(command)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Yale().run();
    }
}

