package tesseract.main;

import tesseract.command.Command;

import tesseract.main.Date;
import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;
import tesseract.main.TesseractException;

import tesseract.task.Deadline;
import tesseract.task.Event;
import tesseract.task.Task;
import tesseract.task.Todo;

/**
 * Main driving class of Tesseract.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Tesseract {
    static final String SCHEDULE_PATH = "src/main/Data/Schedule.txt";

    /** A storage to access memory from hard disk */
    private Storage storage;
    /** A list of all current tasks */
    private TaskList taskList;
    /** A UI to interact with user */
    private TessUi ui;

    Tesseract(String filePath) {
        this.storage = new Storage(filePath); // try getstorage
        this.ui = new TessUi();
        try {
            taskList = new TaskList(storage.getStorage());
        } catch (TesseractException e) {
            ui.showError(e.getErrMsg());
            taskList = new TaskList();
        }
    }

    /**
     * Main driver of the AI.
     */
    public static void main(String[] args) {
        new Tesseract(SCHEDULE_PATH).run();
    }

    /**
     * Run the system.
     */
    public void run() {
        // greet the user
        ui.sayHi();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCmd = ui.readCommand();
                Command.process(fullCmd, taskList.size()); // throw any possible error
                Command cmd = Command.generate(fullCmd); // command is guaranteed to be valid
                cmd.execute(taskList, ui, storage);
                isExit = cmd.isExit();
            } catch (TesseractException e) {
                ui.showError(e.getErrMsg());
            }
        }
    }
}