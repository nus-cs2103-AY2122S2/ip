package tasks;

public class Deadline extends task {
    public String deadline;

    public Deadline (String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.deadline + ")";
    }
}