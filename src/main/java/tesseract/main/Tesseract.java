package tesseract.main;

import javafx.application.Application;
import tesseract.command.Command;



/**
 * Main driving class of Tesseract.
 * @author Fan Jue
 * @version 0.2.0
 * @since 0.1.0
 */
public class Tesseract {
    private static final String DIRECTORY = "data";
    private static final String SCHEDULE_PATH = DIRECTORY + "/Schedule.txt";

    /** A storage to access memory from hard disk */
    private final Storage storage;
    /** A list of all current tasks */
    private TaskList taskList;
    /** A UI to interact with user */
    private final TessUi ui;

    /**
     * Constructor of a Tesseract.
     */
    public Tesseract() {
        this.storage = new Storage(DIRECTORY, SCHEDULE_PATH); // try getStorage
        this.ui = new TessUi();
        try {
            taskList = new TaskList(storage.getStorage());
        } catch (TesseractException e) {
            taskList = new TaskList();
            ui.showError(e.getErrMsg());
        }
    }

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

    public String getWelcomeMsg() {
        return ui.sayHi();
    }

    public String getExitMsg() {
        return ui.sayBye();
    }

    public String admitBug() {
        return ui.admitBug();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
