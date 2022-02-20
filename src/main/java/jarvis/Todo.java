package jarvis;

/**
 * Represents a task to do.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class Todo extends Task {
    /**
     * Returns a Todo object with a description of the task.
     *
     * @param description description of the task to be done
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This method is used to format a Todo object into a String which can then be stored in the text file.
     *
     * @return String This returns the String which details the task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * This method is used to compare if two Task objects are equal or not. Two Task objects are equal if
     * they have the same description and same status.
     *
     * @param o The object to compare with
     * @return boolean This returns true if they are equal and false if they are not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo task = (Todo) o;
        boolean sameDescription = task.getDescription().equals(this.getDescription());
        boolean sameStatus = (task.isDone() == this.isDone());
        return sameDescription && sameStatus;
    }
}
