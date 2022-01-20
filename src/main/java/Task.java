public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" + this.toString());
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n" + this.toString());
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + this.description
                : "[ ] " + this.description;
    }
}
