package spike;

import spike.command.Command;
import spike.command.ExitCommand;
import spike.exception.SpikeException;
import spike.parser.Parser;
import spike.storage.Storage;
import spike.task.TaskList;
import spike.ui.Ui;

/**
 * Starting point of the Spike chatbot program.
 * Initializes all necessary components of the application and starts to accept commands.
 */
public class Spike {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Default constructor for a Spike instance.
     *
     * @param directory relative path of folder where the data file sits
     * @param filePath relative path of the data file
     * @return A Spike instance with all components initialized
     */
    public Spike(String directory, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(directory, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SpikeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.greet(tasks.getListSize());
        Command command;
        do {
            String input = ui.getCommand();
            command = new Parser().parseCommand(input, tasks);
            String executionResult = command.execute(tasks);
            ui.printMsg(executionResult);
        } while (!(command instanceof ExitCommand));
        storage.saveChanges(tasks);
        System.exit(0);
    }

    /**
     * Entry point of the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Spike("/data", "/data/Spike.txt").run();
        return;
    }
}
