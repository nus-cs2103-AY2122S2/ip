import duke.*;
import duke.command.Command;
import duke.exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private List tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new List(storage.load());
        try {
            storage.printFileContent("data/duke.txt");
        } catch (IOException e) {
        }
    }

    public String getResponse(String input) {
        String tempResult = "";
        try {
            Command c = Parser.parseUserInput(input);
            if (c != null) {
                try {
                    tempResult = c.execute(tasks, ui, storage);
                } catch (IOException e) {
                    tempResult = e.getMessage();
                }
            }
        } catch (DukeException e) {
            tempResult = e.getMessage();
        }
        return tempResult;
    }
}
