public class Task {
    protected String description;
    protected String task;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("Got it. I've added this task:");
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }
}