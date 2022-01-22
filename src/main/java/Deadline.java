public class Deadline extends Task {
    String time;
    public Deadline(String des, String time) {
        super(des, TaskType.DEADLINE);
        this.time = time;
    }

    public Deadline(String des, String time, boolean isDone) {
        super(des, TaskType.EVENT, isDone);
        this.time = time;
    }

    @Override
    public String parseTask() {
        return "D | " + Boolean.toString(isDone) + " | " + description + " | " + time;
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
