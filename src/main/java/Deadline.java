public class Deadline extends Task {
    NotableDate date;

    public Deadline(String des, NotableDate date) {
        super(des, TaskType.DEADLINE);
        this.date = date;
    }

    @Override
    public String toString() {
        if (isDone) {
            return  "[D][X] " + description + " (by: " + date + ")";
        } else {
            return "[D][ ] " + description + " (by: " + date + ")";
        }
    }
}
