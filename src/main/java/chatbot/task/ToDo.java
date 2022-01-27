package chatbot.task;

/**
 * Task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructs a to-do with the specified description.
     *
     * @param desc the description of the to-do
     */
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
