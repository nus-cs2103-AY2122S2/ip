package duke.task;

public class ToDo extends Task {
    /**
     * To-do class.
     *
     * @param task tasks for to-do.
     */
    public ToDo(String task) {
        super(task.trim());
    }

    /**
     * To-do class change done status.
     *
     * @param task tasks for deadline.
     * @param done done status.
     */
    public ToDo(String task, boolean done) {
        super(task, done);
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
        int done = super.getDoneStatus();
        return Type.T + " | " + done + " | " + super.getTask();
    }

    @Override
    public String toString() {
        return "[" + Type.T + "]" + super.toString();
    }
}
