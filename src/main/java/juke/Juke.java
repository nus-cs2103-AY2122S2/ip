package juke;

import juke.command.CommandHandler;
import juke.common.Storage;
import juke.common.TaskList;
import juke.common.Ui;

/**
 * Entry point for the Juke application.
 */
public class Juke {
    private static final Juke INSTANCE = new Juke();
    
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private boolean hasExited;
    
    /**
     * Constructor that initializes the application.
     */
    public Juke() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(this);
        this.hasExited = false;
        CommandHandler.registerCommands();
    }
    
    private void run() {
        this.ui.greet();
        this.storage.loadTasks();
        while (!this.hasExited) {
            this.ui.runUiLoop();
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
    public Ui getUi() {
        return this.ui;
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
     * Main class for the Juke application.
     *
     * @param args Running arguments, not used.
     */
    public static void main(String[] args) {
        INSTANCE.run();
    }
}
