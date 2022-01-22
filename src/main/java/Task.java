/**
 * Task class represents the tasks created by the user.
 * It keeps track of the task's description and its status.
 */
public class Task {
    private String description;
    private boolean isDone;
    private TaskType type;

    /**
     * Class constructor specifying the task's description and type.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns status icon "X" of the task.
     * If the task is not done, " " is returned.
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public TaskType getType() {
        return type;
    }

    public String getTypeAsPrefix() {
        switch(type) {
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return "T";
        }
    }

    /**
     * Returns status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}