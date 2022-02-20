package juke;

import javafx.application.Application;
import javafx.application.Platform;
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
    /**
     * Singleton instance of Juke.
     */
    private static final Juke INSTANCE = new Juke();

    private TaskList taskList;
    private TextUi textUi;
    private Gui gui;
    private Storage storage;

    /**
     * Boolean to determine whether the program should exit.
     */
    private boolean hasExited;

    /**
     * Constructor that initializes the application.
     */
    public Juke() {
        initializeFields();
        CommandHandler.registerCommands();
    }

    /**
     * Runs the CLI implementation of Juke.
     *
     * @param args Run arguments, unused.
     */
    public static void main(String[] args) {
        INSTANCE.run();
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
     * Runs the GUI implementation of Juke.
     *
     * @param stage JavaFX stage of the application.
     */
    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(true);
        INSTANCE.getStorage().loadTasks();
        gui.initializeUiComponents(stage);
    }

    /**
     * Begins the process of exiting Juke.
     */
    public void exit() {
        hasExited = true;
        Platform.exit();
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
     * Initializes the fields for the constructor.
     */
    private void initializeFields() {
        taskList = new TaskList();
        textUi = new TextUi();
        gui = new Gui();
        storage = new Storage(this);
        hasExited = false;
    }
}
