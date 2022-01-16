public class Task {

    private String task;
    private boolean completed;

    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        StringBuilder status = new StringBuilder();
        if (this.completed) {
            status.append("[X] ");
        } else {
            status.append("[ ] ");
        }
        return status.append(this.task).toString();
    }
}
