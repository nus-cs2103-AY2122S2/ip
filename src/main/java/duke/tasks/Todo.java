package duke.tasks;

import java.time.LocalDateTime;

public class Todo extends Task{

    public Todo(String taskName) {
        this.taskName = taskName;
    }

    public char getType() {
        return 'T';
    }

    /**
     * Gets the LocalDateTime object associated to this event, if it exists.
     * Returns null if it does not exist.
     *
     * @return The LocalDateTime object containing the Date of the event, null if it does not exist.
     * @see Event#getDate()
     */
    @Override
    public LocalDateTime getDateObj() {
        return null;
    }

    @Override
    public String getDate() {
        return "";
    }

    /**
     * Returns whether if the given object is equals to this event.
     * The given object will be equals to this object if and only if
     * it is of the same task type and has the same name.
     *
     * @param o The object to be compared to.
     * @return true if they are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Todo)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Todo todo = (Todo) o;

        return todo.taskName.equals(this.taskName);
    }
}