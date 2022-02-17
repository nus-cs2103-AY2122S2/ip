package duke;

/**
 * Represents a todo task
 */
class ToDo extends Task {

    public ToDo(String task) {
        super(task, "T");
    }

    public String getTaskTime() {
        return "";
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[" + super.getType() + "][x] " + super.getTaskName();
        } else {
            return "[" + super.getType() + "][ ] " + super.getTaskName();
        }
    }
}
