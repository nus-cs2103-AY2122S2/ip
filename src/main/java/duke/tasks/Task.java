package duke.tasks;

import java.io.Serializable;

import duke.exceptions.InvalidOperationException;

/**
 * Skeletal implementation of a Task Object.
 */
public abstract class Task implements Serializable {

    /**
     * Marks Task Object as done.
     *
     * @throws InvalidOperationException if Task Object is already done
     */
    public abstract void mark() throws InvalidOperationException;

    /**
     * Marks Task Object as undone.
     *
     * @throws InvalidOperationException if Task Object is undone
     */
    public abstract void unmark() throws InvalidOperationException;
}
