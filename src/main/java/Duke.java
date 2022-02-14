import Duke.Exception.DukeException;
import Duke.Processing.Parser;
import Duke.Processing.Storage;
import Duke.Processing.TaskList;
import Duke.UI.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (DukeException e) {
            ui.errorMessage(e);
        }
    }
    public Duke() {
    }

    public void run() {
        ui.startmessage();
        String task = ui.read();
        while(!task.equalsIgnoreCase("bye")) {
            ui.divider();
            try {
                Parser.use(task, this.tasks);
            } catch (DukeException e) {
                ui.errorMessage(e);
            }
            task = ui.read();
        }
        try {
            this.storage.write(tasks);
        } catch (DukeException e) {
            ui.errorMessage(e);
        }
        ui.endmessage();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "I'll add the following to the list\n " + input;
    }




    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    public static void main(String[] args) {
        new Duke("Previously.txt").run();
    }
        
}
