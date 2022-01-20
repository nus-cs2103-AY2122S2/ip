package yalebot;

public class Task {
    private String name;
    private boolean isMarked = false;
    public Task(String name, boolean isMarked) {
        this.name = name;
        this.isMarked = isMarked;

    }

    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    public void markItem() {
        isMarked = true;
        System.out.println("Nice! I've marked this task as done:\n"
                + "  " + getStatusIcon() + " " + name);
    }

    public void unmarkItem() {
        isMarked = false;
        System.out.println("OK, I've marked this task as not done yet:\n"
                + "  " + getStatusIcon() + " " + name);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.name;
    }
}
