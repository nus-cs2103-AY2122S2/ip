package Tasks;

// can actually change this to an Abstract Class
public abstract class Task {
    private String task;
    private boolean markStatus;

    public abstract String getStringCmd();

    public Task (String task, boolean markStatus) {
        this.task = task;
        this.markStatus = markStatus;
    }

    public String getMarkStatus() {
        if (markStatus) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getTask() {
        return this.task;
    }

    public void mark() {
        this.markStatus = true;
    }

    public void unmark() {
        this.markStatus = false;
    }

    @Override
    public String toString() {
        return this.getMarkStatus() + this.getTask();
    }
}
