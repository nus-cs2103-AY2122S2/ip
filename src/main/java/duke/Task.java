package duke;
public class Task {
    String name;
    boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        done = true;
    }

    public void unMarkDone() {
        done = false;
    }

    public static boolean isDeadline(Task t) {
        return t instanceof Deadline;
    }

    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
