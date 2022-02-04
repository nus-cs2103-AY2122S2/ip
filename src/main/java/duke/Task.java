package duke;

/**
 * This is an abstract Task class that creates Task instances with a title,
 * whether task is completed and the type of Task.
 *
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public abstract class Task {
    protected String name;
    protected int done;
    protected Character type;

    public Task(String n, int d) {
        name = n;
        done = d;
    }

    public String getName() {
        return name;
    }

    public int getDone() {
        return done;
    }

    public Character getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public void setType(Character type) {
        this.type = type;
    }

    /**
     * Sets the Task's type of: Todo (T), Deadline (D), Event (E)
     */
    public String getTaskIcon() {
        return String.format("%c", type);
    }
}
