/**
 * The type To do.
 */
public class ToDo extends Task{

    /**
     * Instantiates a new To do.
     *
     * @param taskName the task name
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
