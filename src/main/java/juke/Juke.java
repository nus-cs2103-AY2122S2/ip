package juke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        this.taskList = new TaskList();
        this.textUi = new TextUi();
        this.gui = new Gui();
        this.storage = new Storage(this);
        this.hasExited = false;
        CommandHandler.registerCommands();
    }

    @Override
    public void start(Stage stage) {
        this.gui.initializeUiComponents(stage);
        this.gui.formatUiComponents(stage);
        this.gui.handleEventListeners(stage);
    }

    /**
     * Runs Juke CLI.
     */
    private void run() {
        this.textUi.greet();
        this.storage.loadTasks();
        while (!this.hasExited) {
            this.textUi.runUiLoop();
        }
    }

    /**
     * Begins the process of exiting Juke.
     */
    public void exit() {
        this.hasExited = true;
    }

    /**
     * Returns the list used to store tasks.
     *
     * @return TaskList.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the UI class used to handle inputs and outputs.
     *
     * @return Ui.
     */
    public TextUi getUi() {
        return this.textUi;
    }

    /**
     * Returns the storage class used to handle file storage.
     *
     * @return Storage.
     */
    public Storage getStorage() {
        return this.storage;
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
     * Entry point main method for Juke CLI.
     *
     * @param args Run arguments, unused.
     */
    public static void main(String[] args) {
        INSTANCE.run();
    }
}
