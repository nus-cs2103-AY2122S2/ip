package duke.commands;

/**
 * Represents a generic store command recognized by the parser.
 * Upon execution, it will attempt to store a task into
 * the task list.
 * StoreCommand object stores the tokens which represents the input of the user.
 */
public abstract class StoreCommand extends Command {
    protected String[] tokens;

    /**
     * Constructor of any store command. Specifies that a store command
     * requires storage of data to file.
     */
    public StoreCommand() {
        modifyData = true;
        exitProgram = false;
    }
    /**
     * Stores the user input entirely for the creation of the task.
     *
     * @param tokens a String array that represents the user input.
     */
    @Override
    public void handleParam(String[] tokens) {
        this.tokens = tokens;
    }

}
