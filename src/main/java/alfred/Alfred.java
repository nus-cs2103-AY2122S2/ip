package alfred;

import alfred.command.Command;
import alfred.exceptions.AlfredException;
import java.io.File;
import alfred.parser.AlfredParser;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

public class Alfred {
    private AlfredUserInterface ui;
    private AlfredParser parser;
    private AlfredStorage storage;

    public Alfred() {
        this.ui = new AlfredUserInterface();
        this.parser = new AlfredParser();
        this.storage = new AlfredStorage(String.join(File.separator, ".", "data", "Alfred.txt"));
    }


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