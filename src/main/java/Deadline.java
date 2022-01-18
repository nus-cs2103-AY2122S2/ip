public class Deadline extends Task {
    protected String type;
    protected String time;

    Deadline(String name, String time) {
        super(name);
        this.time = time;
        this.type = "D";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + this.type + "][" + status + "]" + this.name + " (by: "
                + this.time + ")";
    }
}
