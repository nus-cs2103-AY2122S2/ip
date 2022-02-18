package duke;

import java.io.IOException;

/**
 * Represents a command
 */
public abstract class Command {

    private final String command;

    /**
     * Constructor for an instance of Command objects
     *
     * @param command A string representing the command.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Checks if the command is "bye".
     *
     * @return true if command is "bye".
     */
    public boolean isExit() {
        return this.command.equals("bye");
    }

    /**
     * Fetches the keyword of the command.
     *
     * @return A string equal to the first word of the command.
     */
    public String getFirstWord() {
        return this.command;
    }

    /**
     * Executes the command
     *
     * @param tasks TaskList to be altered.
     * @param places PlaceList to be altered.
     * @param ui Ui for reading user input and displaying output.
     * @param storageTask Storage to read pre-existing tasks, and update tasks to hard disk.
     * @param storagePlace Storage to read pre-existing places, and update places to hard disk.
     * @return a String representing the response to the user input.
     * @throws IOException if an I/O error occurs.
     */
    public abstract String execute(TaskList tasks, PlaceList places, Ui ui,
                                   Storage storageTask, Storage storagePlace) throws IOException;
}
