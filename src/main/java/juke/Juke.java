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
    private static Juke instance = new Juke();

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
        taskList = new TaskList(this);
        textUi = new TextUi(this);
        gui = new Gui(this);
        storage = new Storage(this);
        hasExited = false;
        instance = this;
        CommandHandler.registerCommands();
    }

    /**
     * Runs the CLI implementation of Juke.
     *
     * @param args Run arguments, unused.
     */
    public static void main(String[] args) {
        instance.run();
    }

    /**
     * Returns the currently running instance of Juke.
     *
     * @return Instance of Juke.
     */
    public static Juke getInstance() {
        return instance;
    }

    /**
     * Runs the GUI implementation of Juke.
     *
     * @param stage JavaFX stage of the application.
     */
    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(true);
        instance.getStorage().loadTasks();
        gui.initializeUiComponents(stage);
        gui.greet();
    }

    /**
     * Pauses exit by 2 seconds, to see exit message.
     *
     * @throws Exception Exception.
     */
    @Override
    public void stop() throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        super.stop();
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
     * Returns the text UI instance used to handle inputs and outputs.
     *
     * @return Text UI.
     */
    public TextUi getTextUi() {
        return textUi;
    }

    /**
     * Returns the GUI instance used to handle inputs and outputs.
     *
     * @return GUI.
     */
    public Gui getGui() {
        return gui;
    }

    /**
     * Returns the storage instance used to handle file storage.
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
}
