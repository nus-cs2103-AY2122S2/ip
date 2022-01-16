public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return this.done;
    }

    public void markDone() {
        this.done = true;

        System.out.println("Awesome! I've marked this task as done:");
        System.out.println(this);
    }

    public void markNotDone() {
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
