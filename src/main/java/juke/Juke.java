package juke;

import javafx.application.Application;
import javafx.stage.Stage;
import juke.command.CommandHandler;
import juke.common.Storage;
import juke.common.TaskList;
import juke.ui.Gui;
import juke.ui.TextUi;

/**
 * Entry point for the Juke application.
 */
public class Juke extends Application {
    private static final Juke INSTANCE = new Juke();

    private TaskList taskList;
    private TextUi textUi;
    private Gui gui;
    private Storage storage;
    private boolean hasExited;

    /**
     * Constructor that initializes the application.
     */
    public Juke() {
        taskList = new TaskList();
        textUi = new TextUi();
        gui = new Gui();
        storage = new Storage(this);
        hasExited = false;
        CommandHandler.registerCommands();
    }

    /**
     * Runs the GUI implementation of Juke.
     *
     * @param stage JavaFX stage of the application.
     */
    @Override
    public void start(Stage stage) {
        gui.initializeUiComponents(stage);
    }

    /**
     * Runs Juke CLI.
     */
    private void run() {
        textUi.greet();
        storage.loadTasks();
        while (!hasExited) {
            textUi.runUiLoop();
        }
    }

    /**
     * Begins the process of exiting Juke.
     */
    public void exit() {
        hasExited = true;
    }

    /**
     * Returns the list used to store tasks.
     *
     * @return TaskList.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns the UI class used to handle inputs and outputs.
     *
     * @return Ui.
     */
    public TextUi getUi() {
        return textUi;
    }

    /**
     * Returns the storage class used to handle file storage.
     *
     * @return Storage.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Returns the singleton instance of Juke.
     *
     * @return Juke.
     */
    public static Juke getInstance() {
        return INSTANCE;
    }

    /**
     * Runs the CLI implementation of Juke.
     *
     * @param args Run arguments, unused.
     */
    public static void main(String[] args) {
        INSTANCE.run();
    }
}
