package duke.duke;
import java.util.ArrayList;

public class Duke {
    private TaskList tasks;
    private final Storage storage;

    public Duke() {
        String filePath = "data/duke.ser";
        storage = new Storage(filePath); // Searching for File
        try {
            tasks = new TaskList(storage.load()); // Loading if Found
        } catch (Exception e) {
            ArrayList<Task> arr = new ArrayList<>();
            tasks = new TaskList(arr); // Creating new file if not
        }
    }

    public String executeInput(String input) {
        Parser reader = new Parser();
        return reader.parse(input, tasks, storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return executeInput(input);
        } catch (Exception e) {
            return "test";
        }
    }
}


