package duke.chi;

import duke.exception.ChiException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.io.IOException;

/**
 * Main class for ChatBot.
 */
public class Chi {

    /** Contains the task data stored in the hard-drive */
    private Storage storage;

    /** Contains the task data stored in the program */
    private TaskList taskList;

    /** Handles interactions with the user */
    private UI ui;

    /** Interprets messages sent by the user */
    private Parser parser;

    /**
     * Instantiates the necessary components to run the application.
     *
     * @param filepath The filepath of task data saved in hard-disk.
     */
    public Chi(String filepath) {
        this.storage = new Storage(filepath);
        this.parser = new Parser();
        this.ui = new UI();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (ChiException e) {
            this.taskList = new TaskList();
        } catch (IOException e) {
            // print error for IO problems
            ui.printErrorMsg("There's something wrong with the IO nyan~!!");
        }
    }

/*
    public void run() {
        ui.printWelcome();
        ui.requestInput(this.taskList, this.storage, this.parser);
        ui.printGoodbye();
    }


    public static void main(String[] args) {
        Chi myBot = new Chi("data/tasks.txt");
        myBot.run();
    }
*/
    public String getResponse(String msg) throws ChiException, IOException {
             return parser.processMessage(msg, taskList, storage);
    }
}
