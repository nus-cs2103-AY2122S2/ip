package tasks;

/**
 * A class that belongs to the Tasks Package.
 * This class encapsulates the logic of how ToDos should be represented in Nexus.
 */
public class ToDos extends Tasks {

    /**
     * Constructs Todos.
     * @param task Task that should be recorded.
     * @param marked Boolean flag for whether a task is completed
     */
    public ToDos(String task, Boolean marked) {
        super(task, marked);
    }

    /**
     * Constructs a string representation of ToDos.
     * @return String representation of ToDos for caching into a pre-constructed file.
     */
    @Override
    public String cacheString() {
        String s = getIsMarked() ? "1" : "0";
        return "T" + "|" + s + "|" + this.getTask();
    }

    /**
     * Creates message to be displayed for ToDos.
     * @return String representation of ToDos.
     */
    @Override
    public String toString() {
        if (this.getIsMarked()) {
            return "[T]" + "[X" + "] " + this.getTask();
        } else {
            return "[T]" + "[ " + "] " + this.getTask();
        }
    }
}
