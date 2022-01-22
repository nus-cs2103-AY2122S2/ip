package tasks;

public class Deadline extends Task {
    public String deadline;

    public Deadline (String name, String deadline) {
        super(name);
        this.deadline = deadline;
        this.info = "D,0," + name + "," + deadline;
    }

    @Override
    public void mark() {
        this.isDone = true;
        this.info = "D,1," + name + "," + deadline;
    }

    @Override
    public void unmark() {
        this.isDone = false;
        this.info = "D,0," + name + "," + deadline;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.deadline + ")";
    }
}