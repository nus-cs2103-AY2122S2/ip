package duke;

import java.io.IOException;


/**
 * Represents a Personal Assistant Chatbot based on Project Duke.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;


    /**
     * Constructor for the Chatbot.
     *
     * @param filePath the filepath for the data file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("File cannot be created. Check directory.");
            System.exit(0);
        }
    }

    /**
     * Processes the user input and returns the response.
     *
     * @param input the user input
     * @return the response to the user's input
     */
    public String getResponse(String input) {
        return ui.userInput(tasks, storage, input);
    }

}
