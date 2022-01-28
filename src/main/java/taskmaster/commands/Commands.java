package taskmaster.commands;

/*
 * This class encapsulates commands that executed
 * according to the user's input
 */

public abstract class Commands {
    protected String command;

    /**
     * Constructor for Command Objects.
     *
     * @param command - The command the user entered
     */

    public Commands(String command) {
        this.command = command;
    }


    /**
     *  Execute Command.
     */

    public abstract void execute();

}
