package gary.task;

/**
 * Represents a Task to be done
 * A completed ToDo Task e.g. T[X] workout
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo Task
     *
     * @param str name of ToDo task
     */
    public ToDo(String str) {
        super(str);
    }

    /**
     * Represents ToDo as a String object
     * e.g. T[ ] toDo_task
     *
     * @return ToDo representation in String
     */
    @Override
    public String toString() {
        return "T" + super.toString();
    }
}
