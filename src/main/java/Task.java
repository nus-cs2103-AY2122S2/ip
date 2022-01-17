public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void markDone() {
        this.done = true;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");
    }

    public void markUndone() {
        this.done = false;
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");
    }

    @Override
    public String toString() {
        String status = done ? "[X]" : "[ ]";
        return status + " " + this.description;
    }
}
