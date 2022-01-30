package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.util.Parser;
import duke.util.Save;
import duke.util.TaskList;

/**
 * Represents a chatbot named Duke.
 */
public class Duke {
    private TaskList taskList;
    private final Save save;

    /**
     * Constructor for Duke chatbot which initialises with a saved history Save, list of tasks TaskList,
     */
    public Duke() {
        this.save = new Save();
        this.taskList = save.taskList();
        //        this.ui = new Ui();
    }

    /**
     * Handles the user input and calls the command requested by user.
     * @param input user input.
     * @return String of executed command.
     */
    public String handleInput(String input) {
        try {
            Command c = Parser.parse(input);
            String output = "\n" + c.execute(this.taskList, this.save);
            if (c instanceof ByeCommand) {
                this.save.save();
            }
            return output;
        } catch (NullPointerException e) {
            return "This command is invalid";
        }
    }

    /**
     * Returns a default response after receiving user input.
     *
     * @param input User input.
     * @return Response.
     */
    public String getResponse(String input) {
        return "Wonka heard: " + input;
    }

}
