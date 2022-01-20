public class Task {
    protected String desc;
    protected boolean isDone = false;
    protected TaskType taskType;

    public Task(String desc, TaskType taskType) {
        this.desc = desc;
        this.taskType = taskType;
    }

    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getType() {
        switch (this.taskType) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
        default:
            return null;
        }
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatus() + " " + desc;
    }

}
