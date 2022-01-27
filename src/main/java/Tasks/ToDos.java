package Tasks;

/**
 * A class that belongs to the Tasks Package.
 * This class encapsulates the logic of how ToDos should be represented in Duke.
 */
public class ToDos extends Tasks {

    /**
     * Constructor for Todos.
     * @param task Task that should be recorded.
     * @param marked Boolean flag for whether a task is completed
     */
    public ToDos(String task, Boolean marked) {
        super(task, marked);
    }

    /**
     * Construct a string representation of ToDos.
     * @return String representation of ToDos for caching into a pre-constructed file.
     */
    @Override
    public String cacheString() {
        String s = getMarked() ? "1" : "0";
        return "T" + "|" + s + "|" + this.getTask();
    }

    /**
     * Message to be displayed for ToDos as a Task in Duke.
     * @return String representation of ToDos.
     */
    @Override
    public String toString() {
        if (this.getMarked()) {
            return "[T]" + "[X" + "] " + this.getTask();
        } else {
            return "[T]" + "[ " + "] " + this.getTask();
        }
    }
}
