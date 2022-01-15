public class Deadline extends Task {
    String time;
    String deadline;

    public Deadline(String deadline, String time) {
        super(deadline);
        this.deadline = deadline;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}
