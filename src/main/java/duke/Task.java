package duke;
public class Task {
    String name;
    boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void unMarkDone() {
        this.done = false;
    }

    public static boolean isDeadline(Task t) {
        return t instanceof Deadline;
    }

    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
