package seedu.duke;

import seedu.commands.Command;
import seedu.storage.Storage;
import seedu.storage.TaskList;

public class Duke {

    private TaskList tasks;
    private final Parser PARSER;
    private final Storage STORAGE;

    public Duke(String filePath) {
        PARSER = new Parser();
        STORAGE = new Storage(filePath);

        try {
            tasks = new TaskList(STORAGE.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) {
        try {
            Command c = PARSER.parse(input);
            String resp = c.execute(tasks);
            STORAGE.saveAll(tasks.getTasks());
            return resp;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
