package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke class which is the main entry point when starting up the application
 */
public class Duke extends Application {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Duke class
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./src/main/data/data.txt", "./src/main/data");
        this.parser = new Parser();

        //Reading arraylist from data.txt
        try {
            this.taskList = storage.load();
        } catch (Exception e) {
            System.out.println(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * method to run the application and start taking in puts
     */
    public void run() {
        ui.startUp();
        String inputData;
        Scanner scanner = new Scanner(System.in);
        boolean hasEnded = false;

        while (!hasEnded) {
            try {
                inputData = scanner.nextLine();
                hasEnded = parser.takeInput(inputData, taskList);
            } catch (DukeException e) {
                System.out.println(e);
                ui.printSeparator();
            }
            storage.storeTasks(taskList.getTasks());
        }
    }

    /**
     * point of entry for the application
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
