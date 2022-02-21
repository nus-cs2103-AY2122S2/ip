package istjbot;

import java.io.IOException;

import istjbot.command.Command;
import istjbot.exception.BotException;
import istjbot.parser.Parser;
import istjbot.storage.Storage;
import istjbot.text.TextList;
import istjbot.ui.Ui;

/**
 * Encapsulates a chat-bot that interacts with a user in a way that
 * the user gives out a command, which is then executed by the bot and shows the
 * result through GUI.
 */
public class IstjBot {
    /** Storage for saving and loading the tasks to and from an external txt file. */
    private Storage storage;
    /** TextList consists of tasks and notes. */
    private TextList texts;
    /** text-part of the User Interface. */
    private Ui ui;

    private boolean isExit = false;
    private boolean hasConstructorError = false;
    private String constructorError = "";

    /**
     * Constructor for IstjBot.
     * Takes in a String filePath and initializes a new Storage.
     * TextList and Ui objects are also initialized.
     *
     * @param filePath Path of the external file used for loading and saving.
     */
    public IstjBot(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.texts = new TextList(storage.load());
        } catch (IOException | BotException e) {
            hasConstructorError = true;
            constructorError = e.getMessage();
            this.texts = new TextList();
        }
    }

    /**
     * Returns a String of response from IstjBot.
     *
     * @param input String of user input.
     * @return String of response from IstjBot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            System.out.println(c.execute(texts, ui, storage));
            return c.execute(texts, ui, storage);
        } catch (BotException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns a boolean whether the IstjBot should cease its operation.
     *
     * @return Boolean that tells whether to cease IstjBot's operation.
     */
    public boolean shouldExitIstjBot() {
        return isExit;
    }

    /**
     * Returns a boolean whether any error occurred when constructing the IstjBot.
     *
     * @return Boolean that tells whether constructor method raised an error.
     */
    public boolean existsConstructorError() {
        return hasConstructorError;
    }

    /**
     * Returns a String of error message when constructing the IstjBot.
     *
     * @return String of error message.
     */
    public String showConstructorError() {
        return constructorError;
    }
}
