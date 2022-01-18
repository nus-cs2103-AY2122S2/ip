public class Task {
    private String description;
    private boolean done;
    private String line = "-------------------------------------------";

    public Task(String details) {
        this.description = details;
        this.done = false;
    }
    public void mark() {
        if (!this.done) {
            this.done = true;
            System.out.println(line);
            System.out.println("Done? Checked it off for you: ");
            System.out.println(this);
            System.out.println(line);
        }
    }
    public void unmark() {
        if (this.done) {
            this.done = false;
            System.out.println(line);
            System.out.println("Not done? Let me put it back for you: ");
            System.out.println(this);
            System.out.println(line);
        }
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + this.description;
        }
        else {
            return "[ ] "+ this.description;
        }

    }
}
