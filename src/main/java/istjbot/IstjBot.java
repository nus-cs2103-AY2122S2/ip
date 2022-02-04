package istjbot;

import java.io.IOException;

import istjbot.command.Command;
import istjbot.exception.BotException;
import istjbot.parser.Parser;
import istjbot.storage.Storage;
import istjbot.task.TaskList;
import istjbot.ui.Ui;

/**
 * Encapsulates a chat-bot that interacts with a user in a way that
 * the user gives out a command, which is then executed by the bot and prints out the
 * result back.
 */
public class IstjBot {
    /** Storage for saving and loading the tasks to and from an external txt file. */
    private Storage storage;
    /** TaskList for actual adding, modifying, and searching of tasks. */
    private TaskList tasks;
    /** Ui for printing out messages to be read by the user. */
    private Ui ui;

    private boolean isExit = false;
    private boolean existsConstructorError = false;
    private String constructorError = "";

    /**
     * Constructor for IstjBot.
     * Takes in a String filePath and initializes a new Storage.
     * TaskList and Ui objects are also initialized.
     *
     * @param filePath Path of the external file used for loading and saving.
     */
    public IstjBot(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());

        } catch (IOException | BotException e) {
            existsConstructorError = true;
            constructorError = e.getMessage();
            this.tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (BotException e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean shouldExitIstjBot() {
        return isExit;
    }

    public boolean existsConstructorError() {
        return existsConstructorError;
    }

    public String showConstructorError() {
        return constructorError;
    }
}
