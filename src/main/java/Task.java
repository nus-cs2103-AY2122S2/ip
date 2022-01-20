public class Task {
    protected String description;
    protected boolean isDone;
    protected String sym;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.sym = " ";
    }

    public Task(String description, String sym) {
        this.description = description;
        this.isDone = false;
        this.sym = sym;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        String output = String.format(" [%s][%s] %s", this.sym, this.getStatusIcon(), this.description);
        System.out.println(output);
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        String output = String.format("  [%s] %s", this.getStatusIcon(), this.description);
        System.out.println(output);
    }

    String getSym() {
        return this.sym;
    }

    @Override
    public String toString() {
        return String.format("added: %s", description);
    }

}
