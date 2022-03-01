package alfred;

import alfred.command.Command;
import alfred.exceptions.AlfredException;
import alfred.parser.AlfredParser;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;
import java.io.File;


/**
 * This class encapsulates the Alfred bot.
 */
public class Alfred {

    // internals
    private final AlfredUserInterface ui;
    private final AlfredParser parser;
    private final AlfredStorage storage;
    private boolean isExit;


    /**
     * Constructs an Alfred object that saves the list data to Alfred.txt in
     * /data of the project root directory.
     */
    public Alfred() {
        this.isExit = false;
        this.ui = new AlfredUserInterface();
        this.parser = new AlfredParser();
        this.storage = new AlfredStorage(
                String.join(File.separator, ".", "data"),
                String.join(File.separator, ".", "data", "Alfred.txt"));
    }

    /**
     * Runs Alfred in the console. To terminate, use the command
     * "bye".
     */
    public void run() {
        this.ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command command = this.parser.parseInput(input);
                command.execute(ui, storage);
                isExit = command.isExit();
            } catch (AlfredException e) {
                ui.handleAlfredException(e);
            }
        }
    }


    /**
     * Returns the appropriate response and modifies the internal
     * data state of Alfred, given a user input.
     *
     * @param input User input.
     * @return String output to user, in response to input.
     */
    public String getResponse(String input) {
        try {
            assert input != null;
            Command command = this.parser.parseInput(input);
            String response = command.response(this.ui, this.storage);
            this.isExit = command.isExit();
            return response;
        } catch (AlfredException e) {
            return e.getMessage();
        }
    }

    public String getGreetingMessage() {
        return this.ui.getGreetingMessage();
    }

    public boolean isExit() {
        return this.isExit;
    }

}