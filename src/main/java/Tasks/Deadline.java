package Tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(int taskId, String name, String by) {
        super(taskId, name);
        this.by = by;

        System.out.println("Got it. I've added this deadline:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
