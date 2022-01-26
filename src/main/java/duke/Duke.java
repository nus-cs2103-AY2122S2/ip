package duke;
import java.io.IOException;

/**
 * Duke class which acts as a chatbot which can save tasks that users input.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke class that runs the chatbot.
     *
     * @param filePath  Filepath to where the data is stored.
     * @throws IOException  If file cannot be created or if the data cannot be loaded.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.makeDirectory();
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot and takes in user input.
     * Prints output to console and saves the tasklist to local drive.
     *
     * @throws IOException  If file cannot be saved.
     */
    public void run() throws IOException {
        // code to run here
        ui.greet();
        Parser parser = new Parser(ui, tasks, storage);
        parser.readUserInput();
    }

    /**
     * Set filepath and run Duke
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String home = System.getProperty("user.home");
        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "duke.txt");
        new Duke(String.valueOf(filePath)).run();
    }
}
