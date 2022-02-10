package duke.commands;

import duke.main.DukeException;
import duke.main.TaskList;

/**
 * Abstract class that represents a Command.
 * A Command is an input given by the user.
 *
 * @param <E> Generic type, as a Command could take in a String or an Integer.
 */
public abstract class Command<E> {
    /**
     * Abstract method that runs a certain command based on the specific class.
     *
     * @param toDoList the user's List of Tasks
     * @param cmd the user input to Burp
     * @throws DukeException if an unknown Command is given
     */
    public abstract void runCommand(TaskList toDoList, E cmd) throws DukeException;
}
