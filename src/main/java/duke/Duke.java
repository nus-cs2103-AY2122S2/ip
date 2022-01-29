package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * This class consists of the main method, and to initialise the program.
 */
public class Duke {

    Ui ui;
    TaskList taskList;
    Storage storage;
    //21/07/2011 10:48 AM

    /**
     * This is the constructor to create a new instance of Duke.
     * @param filePath is the directory for the file location on the computer.
     * @throws IOException if the file is corrupt or file is not found.
     * @throws ParseException if the contents of the file is not in the correct format.
     */
    public Duke (String filePath) throws IOException, ParseException {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<Task>());
        }

    }

    /**
     * Starts the program Duke for the user
     * @throws IOException if the file is corrupt or file is not found.
     * @throws ParseException if the contents of the file is not in the correct format.
     */
    public void run() throws IOException, ParseException {

        Parser parser = new Parser();

        while(true) {
            try {
                Command command = parser.readCommand();
                command.runCommand(taskList, ui, storage);
            } catch (DukeException e) {
                ui.showWrongCommand();
            }
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Duke("data/tasks.txt").run();
    }

}
