package ann;

import ann.commands.Command;
import ann.commands.ExitCommand;
import ann.data.TaskList;
import ann.gui.Ui;
import ann.parser.Parser;
import ann.storage.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the main logic of the app.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class AnnBot extends Application {
    /**
     * Represents the storage component of the program.
     */
    private Storage storage;
    /**
     * Represents the current task list.
     */
    private TaskList tasks;
    /**
     * Represents the manager for the GUI.
     */
    private Ui ui;

    /**
     * Creates a new AnnBot object.
     *
     */
    public AnnBot() {
    }

    /**
     * Starts the program by initializing the storage and task list.
     */
    @Override
    public void init() throws Exception {
        super.init();
        try {
            this.storage = new Storage();
            this.tasks = new TaskList(storage.load());
            this.ui = new Ui();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        ui.start(stage, this);
    }

    /**
     * Returns AnnBot's response to user input.
     * @param input user input String.
     * @return AnnBot's response String.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        command.setTaskList(tasks);
        executeCommand(command);
        return command.getMessage();
    }

    public boolean isExitCommand(String input) {
        return ExitCommand.isExit(Parser.parse(input));
    }

    /**
     * Executes the given Command, saves the updated TaskList to storage
     * and returns a String which is the message of the Command.
     *
     * @param command a Command.
     * @return a String which is the message of the Command.
     * @throws RuntimeException if unable to save to storage.
     */
    private String executeCommand(Command command) {
        try {
            command.setTaskList(tasks);
            command.executeCommand();
            storage.save(tasks);
            return command.getMessage();
        } catch (Storage.StorageOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
