package duke.task;

public class ToDo extends Task {
    /**
     * Represents To-do class.
     *
     * @param task tasks for to-do.
     */
    public ToDo(String task) {
        super(task.trim());
    }

    /**
     * Changes done status.
     *
     * @param task tasks for deadline.
     * @param isDone done status.
     */
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public ToDo mark() {
        return new ToDo(super.getTask(), true);
    }

    @Override
    public ToDo unmark() {
        return new ToDo(super.getTask(), false);
    }

    @Override
    public String saveData() {
        int isDone = super.getDoneStatus();
        return Type.T + " | " + isDone + " | " + super.getTask();
    }

    @Override
    public String toString() {
        return "[" + Type.T + "]" + super.toString();
    }
}
