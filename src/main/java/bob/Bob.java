package bob;

import javafx.fxml.FXML;

/**
 * Driver class containing the main method for entry to program.
 */
public class Bob {
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a Bob instance with storage and taskList instantiated.
     */
    public Bob() {
        String folderPath = "./data";
        String filePath = "./data/bob.txt";
        storage = Storage.createStorage(folderPath, filePath);
        taskList = storage.load();
    }

    /**
     * Passes user input to Bob logic and returns a response.
     *
     * @param input Input into the program.
     * @return Response from the program after processing input.
     */
    @FXML
    public String getResponse(String input) {
        assert storage != null && taskList != null;
        return Parser.parse(input, taskList, storage) + "\n";
    }
}

