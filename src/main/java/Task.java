public class Task {
    String name;
    boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this);
    }

    public void unMarkDone() {
        this.done = false;
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(this);
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
