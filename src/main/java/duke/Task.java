package duke;

/**
 * Provides abstraction of an task in the todoList
 */
public abstract class Task {
    protected boolean finished;
    protected String content;

    /**
     * Contructs an item object
     *
     * @param content description of what to do in String
     */
    public Task(String content) {
        this.content = content;
        this.finished = false;
    }

    /**
     * marks an item as done
     */
    public void finished() {
        this.finished = true;
    }

    /**
     * marks an item as unfinished
     */
    public void notFinished() {
        this.finished = false;
    }

    /**
     * returns a string representation of the task to be read by users.
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        if (finished) {
            return "[X] " + content;
        } else {
            return "[ ] " + content;
        }
    }

    /**
     * returns a string representation of the task to be used in writing to a local file.
     * @return a string representation of the task
     */
    public String toData() {
        if (finished) {
            return "1:" + content;
        } else {
            return "0:" + content;
        }
    }


}
