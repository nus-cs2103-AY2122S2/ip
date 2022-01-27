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

    public void mark() {
        if (!isDone) {
            isDone = true;
            System.out.println("Nice! I've marked this task as done: \n"
                    + toString());
        } else {
            System.out.println("This task is already done! \n"
                    + toString());
        }
    }

    public void unmark() {
        if (isDone) {
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet: \n"
                    + toString());
        } else {
            System.out.println("This task has not been completed! \n"
                    + toString());
        }
    }

    public void delete() {
        System.out.println("Noted. I've removed this task: \n"
                + toString());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
