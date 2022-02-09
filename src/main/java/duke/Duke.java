package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents a Duke chat bot and task management system.
 */
public class Duke extends Application {
    private static final String NAME = "Cindy's Duke Bot";
    private static final String PATH = "./duke.txt";

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Runs the bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Storage storage = new Storage(PATH);
        Ui ui = new Ui(NAME);
        Parser parser = new Parser(sc, taskList);

        ui.boot();
        try {
            storage.loadTo(taskList);
        } catch (DukeException e) {
            System.err.println(e.toString());
        }
        ui.start();
        parser.parse();
        storage.writeFrom(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}








