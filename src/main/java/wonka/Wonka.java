package wonka;

import wonka.command.Command;
import wonka.util.Parser;
import wonka.util.Save;
import wonka.util.TaskList;

/**
 * Represents a chatbot named Wonka.
 */
public class Wonka {
    private TaskList taskList;
    private final Save save;

    /**
     * Constructor for Wonka chatbot which initialises with a saved history Save, list of tasks TaskList,
     */
    public Wonka() {
        this.save = new Save();
        this.taskList = save.taskList();
    }

    /**
     * Handles the user input and calls the command requested by user.
     * @param input user input.
     * @return String of executed command.
     */
    public String handleInput(String input) {
        Command c = Parser.parse(input);
        if (c == null) {
            return "This command is invalid";
        }
        assert c != null : "Command should not be null";
        String output = "\n" + c.execute(this.taskList, this.save);
        return output;
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
