public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    void done() {
        this.isDone = true;
    }

    void undone() {
        this.isDone = false;
    }

    String finished() {
        if(this.isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    @Override
    public String toString() {
        return String.format(this.description); 
    }
}
