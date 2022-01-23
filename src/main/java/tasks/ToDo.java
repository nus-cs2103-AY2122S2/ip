package tasks;

public class ToDo extends Task {
    private String description;
    private boolean done;
    private String line = "-------------------------------------------";

    public ToDo(String details) {
        this.description = details;

    }

    @Override
    public void mark() {
        if (!this.done) {
            this.done = true;
            System.out.println(line);
            System.out.println("Done? Checked it off for you:");
            System.out.println(this);
            System.out.println(line);
        }

    }

    @Override
    public void unmark() {
        if (this.done) {
            this.done = false;
            System.out.println(line);
            System.out.println("Not done? Let me put it back for you:");
            System.out.println(this);
            System.out.println(line);
        }

    }

    @Override
    public String toString() {
        if (done) {
            return "[T]"+"[X] " + this.description;
        }
        else {
            return "[T]"+"[ ] "+ this.description;
        }
    }

}
