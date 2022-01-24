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
        }

    }

    @Override
    public void unmark() {
        if (this.done) {
            this.done = false;
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
