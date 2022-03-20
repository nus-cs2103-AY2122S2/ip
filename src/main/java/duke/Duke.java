package duke;

import duke.exceptions.DukeExceptions;

import java.io.IOException;
import java.util.Scanner;
import javafx.util.Pair;


/**
 * Programme which serves as an interactive checklist.
 */
public class Duke {

    private duke.Storage storage = new duke.Storage();
    private duke.TaskList tasks;
    private duke.Ui ui;
    private duke.Parser parser = new duke.Parser();

    /**
     * Initializes Duke.
     * @param filePath represents the path of the file and existing tasks to be loaded
     *                 if already present.
     */
    public Duke(String filePath) {
        ui = new duke.Ui();
        storage = new duke.Storage(filePath);
        try {
            tasks = storage.load();
            //tasks = new duke.TaskList();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new duke.TaskList();
        }
    }


    /**
     * Initializes Duke.
     *
     */
    public Duke() {
        ui = new duke.Ui();
        try {
            tasks = storage.load();
            //tasks = new duke.TaskList();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new duke.TaskList();
        }
    }

    /**
     * Returns the appropriate response to the input.
     * @param input
     * @return
     * @throws IOException
     * @throws DukeExceptions
     */
    public String getResponse(String input) throws IOException, DukeExceptions {

        try {
            Pair<duke.TaskList , String> pair = parser.parse(ui, tasks, input);
            tasks = pair.getKey();
            String output = pair.getValue();
            System.out.println(output);
            if (input.equals("bye")) {
                return "BYEBYE PIKACHU WILL MISS U";
            }
            storage.save(tasks);
            return "Pikachu heard you say: "+input+ System.lineSeparator() + output;
        } catch(DukeExceptions e) {
            return e.getMessage();
        }

    }
}








