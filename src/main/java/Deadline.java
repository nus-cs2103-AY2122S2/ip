public class Deadline extends Task {
    String time;
    public Deadline(String des, String time) {
        super(des, TaskType.DEADLINE);
        this.time = time;
    }

    @Override
    public String toString() {
        if (isDone) {
            return  "[D][X] " + description + " (by: " + time + ")";
        } else {
            return "[D][ ] " + description + " (by: " + time + ")";
        }
    }
}
