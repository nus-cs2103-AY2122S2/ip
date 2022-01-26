package Tasks;

public class Task {
    private String task;
    private Boolean marked;

    public Task(String task, Boolean marked) {
        this.task = task;
        this.marked = marked;
    }

    public void setMarked(Boolean var) {
        this.marked = var;
    }

    public String getTask() {
        return this.task;
    }

    public boolean getMarked() {
        return this.marked;
    }

    @Override
    public String toString() {
        if (this.marked) {
            return "[X" + "] " + this.task;
        } else {
            return "[ " + "] " + this.task;
        }
    }
}
