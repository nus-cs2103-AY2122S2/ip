package bob.task;

/**
 * {@inheritDoc}
 */
public class Todo extends Task {

    /**
     * Constructor for a task the user has to do.
     * @param name the name of the task
     */
    public Todo(String name) {
        super(name);
        super.setType("T");
        super.unmarkTask();
    }

    @Override
    public String printStatus() {
        return "[T] " + super.getStatusSymbol() + " " + this;
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline || o instanceof Event) {
            return 1;
        } else {
            return 0;
        }
    }
}
