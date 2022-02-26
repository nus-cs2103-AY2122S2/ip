import java.util.Objects;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String isDone, String description) {
        this.isDone = isDone.equals("1");
        this.description = description;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    private void changeStatusIcon() {
        this.isDone = !isDone;
    }

    private String getStatusIconFileFormat() {
        return (isDone ? "1" : "0");
    }

    public void markTaskAsDone() {
        if (isDone) {
            System.out.println("This task is already marked as done!");
        } else {
            changeStatusIcon();
            System.out.println("Nice! I've marked this task as done:\n  " + this);
        }
    }

    public void markTaskAsUndone() {
        if (!isDone) {
            System.out.println("This task is already marked as undone!");
        } else {
            changeStatusIcon();
            System.out.println("OK, I've marked this task as not done yet:\n  " + this);
        }
    }

    public String fileFormat() {
        return String.format("%s | %s", getStatusIconFileFormat(), description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}