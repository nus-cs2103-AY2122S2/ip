import java.io.IOException;
import java.nio.file.Paths;

import paggro.command.Command;
import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.parser.Parser;
import paggro.storage.Storage;
import paggro.ui.Ui;

/**
 * This class encapsulates a PaggroBot, a chat bot that handles user-given tasks.
 */
public class PaggroBot {
    /** Storage object where inputs are stored. */
    Storage storage;
    /** Ui object which reads from and writes to the user. */
    Ui ui;
    /** Lister object which contains list of tasks and significant dates. */
    Lister lister;

    /**
     * Constructor of PaggroBot.
     * @param filePath Path of file to load stored tasks from and upload tasks to.
     */
    public PaggroBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            lister = storage.loadTasks();
        } catch (PaggroException e) {
            ui.showError(e.getMessage());
            lister = new Lister();
        }
    }

    /**
     * Runs the PaggroBot object to start reading and handling commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command cmd = Parser.parse(input);
                cmd.execute(lister, ui, storage);
                isExit = cmd.isExit();
            } catch (PaggroException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new PaggroBot("./data/paggro.txt").run();
    }
}
