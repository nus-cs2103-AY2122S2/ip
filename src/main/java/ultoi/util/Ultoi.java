package ultoi.util;

import ultoi.command.Command;

import java.nio.file.Path;

/**
 * Represents a bot named Ultoi that could help users memorize their tasks.
 *
 * @author: snoidetx
 * @version 0.0.1
 */
public class Ultoi {
    private Storage storage;
    private TaskList tasks;
    private UltoiUi ui;

    /**
     * Creates a new ultoi.util.Ultoi chatbot.
     *
     * @param filePath Path to the file to load and save tasks.
     */
    public Ultoi(Path filePath) {
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (UltoiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts a chat session.
     */
    public void run() {
        ui.showWelcomeMsg();

        for ( ; ; ) {
            try {
                String input = ui.readInput();
                Command cmd = Parser.parse(input);
                cmd.execute(this.ui, this.tasks, this.storage);
                if (Parser.isBye(input)) {
                    return;
                }
            } catch (UltoiException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Drives the execution of Ultoi bot.
     *
     * @param args User input.
     */
    public static void main(String[] args) {
        Path filePath = java.nio.file.Paths.get(System.getProperty("user.home"),
                "iP", "data", "ultoi.util.Ultoi.txt");
        new Ultoi(filePath).run();
    }
}