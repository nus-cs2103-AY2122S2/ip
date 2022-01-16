public class Deadline extends Action{
    String by;
    String task;

    public Deadline(String task, String by) {
        super(task);
        this.by  = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
