package duke;

import duke.handler.FileHandler;
import duke.handler.Handlers;
import duke.task.Tasklist;

/**
 * Returns the service bot Duke.
 */
public class Duke {

    private Tasklist list;

    /**
     * Returns the bot as an object.
     *
     * @param list Tasklist that is used to store the tasks.
     */
    public Duke(Tasklist list) {
        this.list = list;
        FileHandler.readFromFile(this.list);
    }

    /**
     * Greets the user upon startup.
     *
     * @return Customized greeting.
     */
    public static String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        StringBuilder str = new StringBuilder("Hello from\n");
        return str.append(logo).append("What can I do for you?\n").toString();
    }

    /**
     * Returns responses from Duke to the GUI.
     *
     * @param input Input from user.
     * @return Response from Duke.
     */
    public String handleInput(String input) {
        return Handlers.commandHandler(this.list, input);
    }
}
