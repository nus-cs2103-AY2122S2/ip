public class Deadline extends Task{
    private String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        String mark;
        if (super.getDone()) {
            mark = "[X] ";
        } else {
            mark = "[ ] ";
        }
        return "[D]" + mark + getName() +
                " (by: " + this.time + ")";
    }
}
