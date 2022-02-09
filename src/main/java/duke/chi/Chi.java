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
            ui.printErrorMsg("There's something wrong with the IO nyan~!!");
        }
    }

    /**
     * Returns a response to the message sent by the user.
     *
     * @param msg The message sent by the user.
     * @return A String of the response.
     * @throws ChiException If there are problems with the format or content of the message sent.
     * @throws IOException If there are problems with the I/O during message processing.
     */
    public String getResponse(String msg) throws ChiException, IOException {
        return parser.processMessage(msg, taskList, storage);
    }
}
