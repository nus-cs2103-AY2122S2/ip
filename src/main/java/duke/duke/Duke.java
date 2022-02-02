package duke.duke;
import java.util.ArrayList;


public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    public Duke() {
        ui = new Ui(); // Setting up new UI
        String filePath = "data/duke.ser";
        storage = new Storage(filePath); // Searching for File
        try {
            tasks = new TaskList(storage.load()); // Loading if Found
        } catch (Exception e) {
            ArrayList<Task> arr = new ArrayList<>();
            tasks = new TaskList(arr); // Creating new file if not
        }
    }

    public void run() {
        ui.showWelcome(); // Welcome Page
        Parser reader = new Parser();
        while (true) {
            String fullCommand = ui.readCommand();
            reader.parse(fullCommand, tasks, storage);
            ui.showLine();
            if (fullCommand.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}


