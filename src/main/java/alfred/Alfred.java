package alfred;

import alfred.parser.AlfredParser;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;
import alfred.command.Command;
import alfred.exceptions.AlfredException;
import java.io.File;

/**
 * This class encapsulates the Alfred bot.
 */
public class Alfred {
    private AlfredUserInterface ui;
    private AlfredParser parser;
    private AlfredStorage storage;


    /**
     * Constructs an Alfred object that saves the list data to Alfred.txt in
     * /data of the project root directory.
     */
    public Alfred() {
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
}