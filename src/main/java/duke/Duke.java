package duke;

import duke.admin.Parser;
import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.commands.Command;
import duke.exceptions.DukeException;
/**
 * Duke class is the main class of the program.
 */
public class Duke {
    private static String FILE_PATH = ".\\src\\main\\java\\duke\\data\\duke.txt";
    private Storage storage;
    private TaskList tasks = new TaskList();

    /**
     * Constructor of Duke that takes in a file path specifying the location of the
     * storage file.
     * @param filePath file path specifying the location of the storage file
     */
    public Duke() {
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
        }
    }

    /**
     * Initiates the program and outlines the operation backbone of the program.
     */
    // public void run() {
    //     ui.welcome();
    //     boolean isExit = false;
    //     while (!isExit) {
    //         try {
    //             String fullCommand = ui.readCommand();
    //             ui.showLine(); // show the divider line ("_______")
    //             Command c = Parser.parse(fullCommand);
    //             c.execute(tasks, ui, storage);
    //             isExit = c.isExit();
    //         } catch (DukeException e) {
    //             ui.showError(e.getMessage());
    //         } finally {
    //             ui.showLine();
    //         }
    //     }
    // }

    public String getResponse(String fullCommand) throws DukeException {
        Command c;
        try {
            c = Parser.parse(fullCommand);
            String response = c.execute(tasks, storage);
            
            return response;
        } catch (DukeException e) {
            return Ui.showErrorMessage(e.getMessage());
        }

    }
}
