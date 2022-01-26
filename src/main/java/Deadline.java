public class Deadline extends Task {

    public Deadline(String taskType, String description, String date) {
        super(taskType, description, date);
    }

    public Deadline(String taskType, boolean isDone, String description, String date) {
        super(taskType, isDone, description, date);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + super.getDate() + ")";
    }


}