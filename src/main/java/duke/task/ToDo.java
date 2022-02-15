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
     * @param task   tasks for to-do.
     * @param isDone done status.
     */
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Changes done status by generating new to-do.
     *
     * @param task     tasks for to-do.
     * @param isDone   done status.
     * @param priority priority status.
     */
    public ToDo(String task, boolean isDone, Priority priority) {
        super(task, isDone, priority);
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
    public ToDo setPriority(Priority priority) {
        boolean isDone = super.getDoneStatus() == 1;
        return new ToDo(super.getTask(), isDone, priority);
    }

    @Override
    public String saveData() {
        int isDone = super.getDoneStatus();
        return Type.T + " | " + isDone + " | " + super.getPriorityText() + " | " + super.getTask();
    }

    @Override
    public String toString() {
        return "[" + Type.T + "]" + super.toString();
    }
}
