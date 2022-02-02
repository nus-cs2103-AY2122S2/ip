package doge;

import doge.command.Command;
import doge.exception.DogeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the Doge bot where it encapsulates the storage space, user interface and task list.
 */
public class Doge {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for class Doge.
     */
    public Doge() {
        this.storage = new Storage();
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DogeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Doge().run();
    }

    /**
     * Runs Doge bot.
     */
    public void run() {
        boolean isExit = false;
        this.ui.showLine();
        this.ui.greet();
        this.ui.showLine();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                this.ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                storage.save(this.tasks.getTaskList());
                System.out.print(ui.respond(c));
                isExit = c.isExit();
            } catch (DogeException e) {
                ui.showError(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public Ui getUi() {
        return this.ui;
    }

}
