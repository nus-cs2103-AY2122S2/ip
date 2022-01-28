package duke;

import exceptions.DukeException;
import java.io.IOException;

/**
 * Holds all the commands for Duke bot
 * Commands included are Task, Todo, Event, Deadline, list, mark, unmark, find
 * An abstract class with abstract method execute(TaskList, Ui, Storage)
 */
public abstract class Command {
    private boolean isExit = false; // default value is false



    /**
     * Executes according to command
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return String return by ui
     * @throws DukeException
     * @throws IOException
     */
    public abstract String execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Set isExit to true
     */
    public void changeExit() {
        isExit = true;
    }

    /**
     * Getter to return exit status
     * @return boolean isExit
     */
    public boolean isExit() {
        return isExit;
    }
}
