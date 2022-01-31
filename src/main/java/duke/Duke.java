package duke;

import java.io.IOException;


/**
 * Represents a Personal Assistant Chatbot based on Project duke.Duke.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for the Chatbot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("File cannot be created. Check directory.");
            System.exit(0);
        }
    }

    public String getResponse(String input) {
        return ui.userInput(tasks, storage, input);
    }

}
