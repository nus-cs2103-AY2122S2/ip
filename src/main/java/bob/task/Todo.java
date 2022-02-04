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
        super.setStatus(0);
    }

    @Override
    public String printStatus() {
        return "[T] " + statusSymbols[super.getStatus()] + " " + this;
    }
}
