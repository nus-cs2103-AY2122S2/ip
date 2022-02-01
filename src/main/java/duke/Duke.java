package duke;

import java.io.IOException;
import duke.command.Command;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String name;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Duke} object with its name and path for storage.
     */
    public Duke() {
        this.name = "Enkel";
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.dir"));
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs a Duke.
     * @throws IOException if an I/O error occurred
     */
    public void run() throws IOException {
        ui.showLine();
        ui.showMessage(greet());
        ui.showLine();
        while (true) {
            try {
                String input = ui.readInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                storage.update(tasks);
            } catch (ExitException e) {
                storage.update(tasks);
                break;
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns the greetings from Duke.
     * @return greetings from Duke.
     */
    public String greet() {
        return "Hello! I'm " + name + "\nWhat can I do for you?";
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
