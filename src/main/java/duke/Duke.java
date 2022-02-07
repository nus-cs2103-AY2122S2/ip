package duke;

import duke.exception.RonException;
import duke.exception.WriteException;
import duke.parser.InputParser;

/**
 * Custom chatbot adapted from Duke
 * Name of chatbot: Ron
 *
 * @author jaegarpoon
 * @version 0.1
 * @since 2022-02-21
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private String filePath = "data/tasks.txt";
    private InputParser inputParser = new InputParser();

    public Duke() {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (RonException e) {
            this.tasks = new TaskList(filePath);
        }
    }

    public String showWriteError() {
        return new WriteException().toString();
    }

    public String backupTasks() {
        try {
            this.tasks.backup();
            return "Bye. Stay safe and have a nice day!";
        } catch (RonException e) {
            return showWriteError();
        }
    }

    public String getResponse(String input) {
        try {
            return this.inputParser.parseInput(input, tasks);
        } catch (RonException e) {
            return e.toString();
        }
    }
}
