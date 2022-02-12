package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * A Duke with a name.
 */
public class Duke {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String name;
    private Storage storage;
    private TaskList tasks;
    private String initializationMessage;

    /**
     * Constructs a {@code Duke} object with its name.
     */
    public Duke(String name) {
        this.name = name;
        this.storage = new Storage(System.getProperty("user.dir"));
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns the greetings from Duke.
     */
    public String greet() {
        return "Hello! I'm " + name + ".\nWhat can I do for you?";
    }

    /**
     * Returns the response of a user input.
     * @param input the user input
     */
    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks);
            storage.update(tasks);
            if (c.isExit()) {
                return new Response(true, response);
            }
            return new Response(false, response);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

}
