package duke;

import duke.command.Command;

/**
 * Main driver class for task organisation bot Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Duke.
     */
    public Duke() {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(Ui.showLoadingError());
            tasks = new TaskList();
        }
        assert storage != null;
        assert tasks != null;
    }

    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String greet() {
        return Ui.greet();
    }
}
