package athena;

import athena.commands.Command;
import athena.commands.ShutdownCommand;
import athena.exceptions.InputException;
import athena.parser.Parser;
import athena.storage.Storage;
import athena.tasks.TaskList;
import athena.ui.Ui;

import java.io.IOException;

/**
 * Provides the point of entry into the Athena program. Initializes the program and handles
 * the main program logic.
 */
public class Main {
    private static final String SAVE_DIRECTORY = "data";
    private static final String SAVE_FILENAME = "athena.txt";

    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private boolean isActive;

    /**
     * Initializes the program by loading task data from disk if any and initializing all
     * requisite objects and data structures.
     */
    public Main() {
        // Load save data if present
        storage = new Storage(SAVE_DIRECTORY, SAVE_FILENAME);
        initTaskList();
        ui = new Ui(taskList);
        isActive = true;
        ui.sayText("Greetings! My name is Athena. What can I help you with?");
    }

    private void initTaskList() {
        if (storage.hasExistingSave()) {
            try {
                taskList = storage.loadFromDisk();
            } catch (IOException e) {
                ui.sayText("I couldn't load from disk. Opening new task list instead.");
                taskList = new TaskList();
            }
        } else {
            taskList = new TaskList();
        }
    }

    /**
     * Starts running the main logic of the program, which is to keep reading and running user
     * commands until the 'bye' command is given. Also, saves the task list to the disk
     * when modified.
     */
    public void run() {
        while (isActive) {
            String input = ui.readNextLine();
            try {
                Command command = Parser.getCommand(input);
                command.execute(ui, taskList);
                if (command instanceof ShutdownCommand) {
                    isActive = false;
                }
            } catch (InputException e) {
                ui.sayText(e.getMessage());
            }
            SaveIfTaskListModified();
        }
    }

    private void SaveIfTaskListModified() {
        if (taskList.wasModified()) {
            try {
                storage.saveToDisk(taskList);
                taskList.resetLastModified();
            } catch (IOException e) {
                ui.sayText("I encountered a problem saving to disk: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}