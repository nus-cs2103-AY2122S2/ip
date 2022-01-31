package istjbot.task;

/**
 * Encapsulates a Todo task with a description.
 * The date is processed as an empty Optional in the parent class, Task.
 */
public class Todo extends Task {

    /**
     * Constructor for this Todo task.
     *
     * @param description Description for the task.
     */
    public Todo(String description) {
        super(description, null);
    }

    /**
     * Returns a String with a format for the txt file that is to be saved.
     *
     * @return Txt-file formatted String.
     */
    @Override
    public String toTxtString() {
        String marked = this.isDone? "1" : "0";
        String txtString = "todo / " + marked + " / " + this.description;
        return txtString;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
