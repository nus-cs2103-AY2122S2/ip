public class Task {
    String task;
    Boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    // Default Constructor
    public Task() {
        this.task = null;
        this.done = null;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String string;

        if (this.done) {
            string = "[X] " + task;
        } else {
            string = "[ ] " + task;
        }

        return string;
    }
}
