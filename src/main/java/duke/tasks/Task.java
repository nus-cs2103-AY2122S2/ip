package duke.tasks;

import duke.exceptions.InvalidOperationException;

import java.io.Serializable;

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
