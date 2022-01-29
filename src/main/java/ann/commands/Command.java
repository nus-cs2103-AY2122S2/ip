package ann.commands;

import ann.data.TaskList;

/**
 * Represents a user command.
 *
 * @author Hong Anh
 * @version 0.1
 */
public abstract class Command {
    private String message;
    /** Represents the task list that the user is giving commands on. */
    protected TaskList taskList;

    /**
     * Sets the 'message' field to the given string.
     *
     * @param m the message String.
     */
    public void setMessage(String m) {
        message = m;
    }

    /**
     * Returns the 'message' field.
     *
     * @return a String, which is the command's message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the 'taskList' field to the given TaskList.
     *
     * @param tl the user's TaskList.
     */
    public void setTaskList(TaskList tl) {
        taskList = tl;
    }

    /**
     * An abstract method to be implemented by sub-classes.
     * Executes the command.
     */
    public abstract void executeCommand();
}