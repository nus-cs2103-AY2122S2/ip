abstract public class Task {

    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this);
    }

    public void markAsUndone() {
        isDone = false;
        System.out.println(" OK, I've marked this task as not done yet: ");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description.toString();
    }
}
