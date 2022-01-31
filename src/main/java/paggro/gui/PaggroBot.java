package paggro.gui;

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
    private static final String FILE_PATH = "./data/paggro.txt";
    /** True if bye command executed. */
    protected boolean isExit = false;
    /** Ui object which reads from and writes to the user. */
    protected Ui ui;
    /** Storage object where inputs are stored. */
    private Storage storage;
    /** Lister object which contains list of tasks and significant dates. */
    private Lister lister;

    /**
     * Constructor of PaggroBot.
     */
    public PaggroBot() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            lister = storage.loadTasks();
        } catch (PaggroException e) {
            ui.showError(e.getMessage());
            lister = new Lister();
        }
    }

    /**
     * Returns a String response based on the user input.
     * @param input String of the user input.
     * @return String response based on given input.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            response.append(ui.showLine());
            Command cmd = Parser.parse(input);
            response.append(cmd.execute(lister, ui, storage));
            isExit = cmd.isExit();
        } catch (PaggroException e) {
            response.append(ui.showError(e.getMessage()));
        } finally {
            response.append(ui.showLine());
            return response.toString();
        }
    }
}
