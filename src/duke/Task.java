package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is an abstract duke.Task class that creates task instances with a title,
 * whether task is completed and the type of task.
 *
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public abstract class Task {
    protected String name;
    protected int isDone;
    protected Character type;

    public Task(String n, int d) {
        name = n;
        isDone = d;
    }

    public String getName() {
        return name;
    }

    public int getIsDone() {
        return isDone;
    }

    public Character getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public void setType(Character type) {
        this.type = type;
    }

    /**
     * Sets the duke.Task's type of: Todo, duke.Deadline, duke.Event
     */
    public String getTaskIcon() {
        return String.format("%c", type);
    }
}
