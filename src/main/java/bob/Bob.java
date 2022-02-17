package bob;

import javafx.fxml.FXML;

/**
 * Driver class containing the main method for entry to program.
 */
public class Bob {
    private Storage storage;
    private TaskList taskList;

    Bob() {
        String folderPath = "./data";
        String filePath = "./data/bob.txt";
        storage = Storage.createStorage(folderPath, filePath);
        taskList = storage.load();
    }

    @FXML
    public String getResponse(String input) {
        assert storage != null && taskList != null;
        return Parser.parse(input, taskList, storage);
    }
}

