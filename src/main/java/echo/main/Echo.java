package echo.main;

import java.io.FileNotFoundException;

import echo.command.Command;
import echo.parser.Parser;
import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;


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
        } catch (FileNotFoundException fnf) {
            UI.welcomeNewUser();
            tasks = new TaskList();
        } catch (EchoException e) {
            UI.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Run echo.
     */
    public void run() {
        UI.showWelcome();
        String fullCommand = UI.readCommand();
        while (!fullCommand.equals("bye")) {
            try {
                UI.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, UI, STORAGE);
            } catch (EchoException e) {
                UI.showError(e.getMessage());
            } finally {
                UI.showLine();
                fullCommand = UI.readCommand();
            }
        }
        UI.sayBye();
    }

    /**
     * Main.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        String filePath = System.getProperty("user.home") + "/git/CS2103ip/data/echo.txt";
        new Echo(filePath).run();
    }
}
