package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.Ui.Main;
import duke.Ui.Ui;
import duke.command.Command;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import javafx.application.Application;

/**
 * Represents the Duke Application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class Duke {

    private static TaskList taskList;
    private static Storage storage;
    private static final Parser parser = new Parser();

    /**
     * Initialization of Duke application.
     */
    public Duke() {
        storage = new Storage(parser);
        try {
            taskList = storage.importTasks();
        } catch (IOException e) {
            // Error
        }
    }

    public String getResponse(String inputTxt) {
        try {
            Command c = parser.parse(inputTxt);
            return c.execute(taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return Ui.MSG_FILEWRITEERROR;
        }
    }

    /**
     * Initiation of the Duke application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
