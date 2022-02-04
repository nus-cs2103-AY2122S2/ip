import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Main class from which the bot is run.
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

    Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.setUpData());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        ui = new Ui(tasks);
        parser = new Parser(tasks);
    }

    /**
     * Starts the bot by combining Parser, Storage, TaskList and Ui and handling Duke exceptions.
     */
    public void run() {
        try {
            ui.startConversation(this.parser, this.storage);
        } catch (DukeException e) {
            ui.showIllegalTextError(e);
            new Duke("data.txt").run();
        }
    }

    /**
     * Runs the bot.
     * @param args but not used.
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke :) \nWhat can I do for you? :D");
        new Duke("data.txt").run();
    }
    //duke
}
