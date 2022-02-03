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

    Tesseract() {
        this.storage = new Storage(SCHEDULE_PATH); // try getStorage
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
     * @param args
     */
    //public static void main(String[] args) {
    //    new Tesseract(SCHEDULE_PATH).run();
    //}

    /**
     * Run the system.
     */
    public String getResponse(String fullCmd) {
        try {
            Command.process(fullCmd, taskList.size()); // throw any possible error
            Command cmd = Command.generate(fullCmd); // command is guaranteed to be valid
            return cmd.execute(taskList, ui, storage);
        } catch (TesseractException e) {
            return e.getErrMsg();
        }
    }


}
