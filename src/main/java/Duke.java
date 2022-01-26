import java.io.IOException;

public class Duke {
    // Attributes
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.mkDir();
        tasks = new TaskList(storage.load());
    }

    public void run() throws IOException {
        // code to run here
        ui.greet();
        Parser parser = new Parser(ui, tasks, storage);
        parser.readUsrInput();
    }

    public static void main(String[] args) throws IOException {
        String home = System.getProperty("user.home");
        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "duke.txt");
        new Duke(String.valueOf(filePath)).run();
    }
}
