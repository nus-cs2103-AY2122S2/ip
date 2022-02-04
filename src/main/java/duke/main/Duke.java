package duke.main;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * The main class for running Duke.
 * This class contains all the abstracted details for Duke.
 */
public class Duke {

    private TaskList toDoList;
    private Storage storage;
    private Ui ui;

    private final String filepath = "./tasklist.txt";

    /**
     * Constructor for Duke.
     * Tries to retrieve the list of Tasks from the default filepath
     */
    public Duke() {
        storage = new Storage(this.filepath);
        ui = new Ui();

        // Attempt to open the storage file. If file does not exist, then create a new file.
        try {
            toDoList = storage.addFileContent();
        } catch (FileNotFoundException e) {
            Ui.handleLoadError();
            // creates a new file in the current directory
            File f = new File(this.filepath);
            toDoList = new TaskList();
        }
    }

    /**
     * Gets Burp's response in return to a command given to it.
     *
     * @param input the user input
     * @return Burp's response
     */
    public String getResponse(String input) {
        try {
            String commandType = input.split(" ")[0];
            ui.burpReply(ui.determineType(commandType), toDoList, input, this.storage);
            return Ui.getDukeResponse();
        } catch (DukeException e) {
            return Ui.getDukeResponseError();
        }
    }
}
