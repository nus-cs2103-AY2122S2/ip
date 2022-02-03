package main;

import command.Command;

/**
 * Main driving class of Tesseract.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Tesseract {
    static final String SCHEDULE_PATH = "src/main/Data/Schedule.txt";

    /** A storage to access memory from hard disk */
    private final Storage storage;
    /** A list of all current tasks */
    private TaskList taskList;
    /** A UI to interact with user */
    private final TessUi ui;

    Tesseract(String filePath) {
        this.storage = new Storage(filePath); // try getStorage
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
