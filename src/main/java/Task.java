public class Task {
    protected int taskId;
    protected String name;
    protected boolean done;

    public Task(int taskId, String name) {
        this.taskId = taskId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return this.done;
    }

    public void mark() {
        this.done = true;

        System.out.println("Awesome! I've marked this task as done:");
        System.out.println(this);
    }

    public void unMark() {
        this.done = false;

        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(this);
    }

    public String getStatusIcon() {
        return this.done ? "[X]" : "[]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getName());
    }
}
