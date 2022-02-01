package istjbot;

import istjbot.command.Command;
import istjbot.exception.BotException;
import istjbot.parser.Parser;
import istjbot.storage.Storage;
import istjbot.task.TaskList;
import istjbot.ui.Ui;

import java.io.IOException;

/**
 * Encapsulates a chat-bot that can interact with the user in a way that
 * user gives out a command, which is then executed by the bot and prints out the
 * result back.
 */
public class IstjBot {
    /** Storage for saving and loading the tasks to and from an external txt file. */
    private Storage storage;
    /** TaskList for actual adding, modifying, and searching of tasks. */
    private TaskList tasks;
    /** Ui for printing out messages to be read by the user. */
    private Ui ui;

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
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the IstjBot.
     * Ui takes in user input, which is passed to Parser, finally to an appropriate Command
     * for execution. Operation terminated with a terminating Command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initiates a new IstjBot and runs it.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new IstjBot("data/tasks.txt").run();
    }
}