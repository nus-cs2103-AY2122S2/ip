package stevie;

import stevie.command.Command;
import stevie.exception.StevieException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

import java.io.File;

/**
 * stevie.Stevie is a class that serves as a user interface to allow access to an underlying
 * task manager. Users can use stevie.Stevie to add in their upcoming tasks/events/deadlines,
 * and to mark them as completed when necessary. If you want to use a different location for
 * your saved data, replace the path field.
 */
public class Stevie {
    /**
     * Path to the save file for task list
     */
    private static String path = "src" + File.separator + "main"
            + File.separator + "data" + File.separator + "tasks.txt";

    /**
     * Task list to store all of user's upcoming tasks
     */
    private final TaskList tasks;

    /**
     * Ui to receive user inputs and output responses
     */
    private final StevieUi ui;

    /**
     * Handles saving and loading of user's tasks
     */
    private final TaskDataHandler storage;

    private Stevie() {
        ui = new StevieUi();
        storage = new TaskDataHandler(path);
        tasks = new TaskList(storage.loadTasks());
    }

    public static void main(String[] args) {
        new Stevie().run();
    }

    private void run() {
        ui.greet();
        boolean isExit = false;
        String userIn;
        while (!isExit) {
            try {
                userIn = ui.getUserInput();
                Command command = StevieParser.parse(userIn);
                isExit = command.execute(tasks, storage, ui);
            } catch (StevieException ex) {
                ui.outputMessage(ex.getMessage());
            }
        }
        ui.terminate();
    }
}
