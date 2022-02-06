package duke.duke;
import java.util.ArrayList;

public class Duke {
    private TaskList tasks;
    private final Storage storage;

    /**
     * Initializes a new Duke class. It locates and loads the previous save file by invoking load()
     * from storage and saves it to an ArrayList.
     */
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

    /**
     * Receives the user input from the GUI and sends it to the parser in order to
     * figure out Duke's response.
     *
     * @param input User input with commands
     * @return Duke's response after parsing the commands.
     */
    public String getResponse(String input) {
        Parser reader = new Parser();
        return reader.parse(input, tasks, storage);
    }
}


