package athena;

import java.io.IOException;

import athena.commands.Command;
import athena.commands.ShutdownCommand;
import athena.exceptions.InputException;
import athena.parser.Parser;
import athena.storage.Storage;
import athena.tasks.TaskList;
import athena.ui.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Provides the point of entry into the Athena program. Initializes the program and handles
 * the main program logic.
 */
public class Athena extends Application {
    private static final String SAVE_DIRECTORY = "data";
    private static final String SAVE_FILENAME = "athena.txt";

    private TaskList taskList;
    private final Storage storage;
    private boolean isActive;

    /**
     * Initializes the program by loading task data from disk if any and initializing all
     * requisite objects and data structures.
     */
    public Athena() {
        storage = new Storage(SAVE_DIRECTORY, SAVE_FILENAME);
        initTaskList(); // Load save data if present
        isActive = true;
    }

    private void initTaskList() {
        if (storage.hasExistingSave()) {
            try {
                taskList = storage.loadFromDisk();
            } catch (IOException e) {
                System.out.println("I couldn't load from disk. Opening new task list instead.");
                taskList = new TaskList();
            }
        } else {
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        String response = "";
        try {
            Command command = Parser.getCommand(input);
            response = command.execute(taskList);
            if (command instanceof ShutdownCommand) {
                isActive = false;
            }
        } catch (InputException e) { // return error message instead.
            return e.getMessage();
        }
        try {
            saveIfTaskListModified();
        } catch (IOException e) {
            response += "\nI encountered a problem saving to disk: " + e.getMessage();
        }
        return response;
    }

    private void saveIfTaskListModified() throws IOException {
        if (taskList.wasModified()) {
            storage.saveToDisk(taskList);
            taskList.setNotModified();
        }
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void start(Stage stage) {
        stage = new MainWindow(new Athena());
        stage.show();
    }
}
