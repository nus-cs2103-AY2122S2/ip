package duke;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.sonautil.DukeException;
import duke.sonautil.Parser;
import duke.sonautil.Storage;
import duke.sonautil.TaskList;
import duke.sonautil.Ui;

/**
 * The bot that responses to commands and collects tasks
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for object Duke
     *
     * @param filePath filePath of the tasklist
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt");
    }

    /**
     * Returns sona's response as string
     *
     * @param input user input
     * @return sona's response
     */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                return Ui.goodbyeMessage();
            }
            String[] command = new Parser().processMessage(input);
            storage.executeCommand(command);
            return tasks.executeCommand(command);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }

    }
}
