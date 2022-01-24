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

    public String getLetter() {
        switch (this.taskType) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return null;
        }
    }

    public String getDesc() {
        return desc;
    }

    public Boolean isDone() {
        return isDone;
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
