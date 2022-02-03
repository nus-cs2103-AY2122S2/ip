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
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (FileNotFoundException | EchoException e) {
            tasks = new TaskList();
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
            return UI.showError(e.getMessage());
        }
    }
}
