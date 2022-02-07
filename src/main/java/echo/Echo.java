package echo;

import java.io.FileNotFoundException;

import echo.command.Command;
import echo.parser.Parser;
import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;
import echo.utils.EchoException;

/**
 * Echo, a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Echo {

    /** Storage deals with loading tasks from the file and saving tasks in the file. */
    private final Storage STORAGE;

    /** TaskList containing list of tasks. */
    private TaskList tasks;

    /** Ui that deals with user interactions. */
    private final Ui UI;

    /**
     * Constructor for Echo.
     *
     * @param filePath File path of the saved task list.
     */
    public Echo(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        tasks = new TaskList();
    }

    /**
     * Returns the opening message.
     *
     * @return Opening message.
     */
    public String getOpeningMessage() {
        return UI.getOpeningMessage();
    }

    /**
     * Returns the status of loading the data file.
     *
     * @return Status of loading data file.
     */
    public String loadFile() {
        try {
            tasks = new TaskList(STORAGE.load());
            return "Loading data file: Success";
        } catch (FileNotFoundException e) {
            return "Data file not found\n" + e.getMessage();
        } catch (EchoException e) {
            return "Loading data file: Failed\n" + e.getMessage();
        }
    }

    /**
     * Gets response to user input.
     *
     * @param input Input from user.
     *
     * @return Response to user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, UI, STORAGE);
        } catch (EchoException e) {
            return UI.getErrorMessage(e.getMessage());
        }
    }
}
