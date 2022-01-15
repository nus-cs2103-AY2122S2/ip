/**
 * Encapsulate information of pure to-do task.
 */
public class ToDo extends Task{
    /**
     * Normal constructor.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
