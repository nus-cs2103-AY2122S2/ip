public class Deadline extends Task{

    String deadline;

    public Deadline(String name, boolean isChecked, String taskLabel, String deadline) {
        super(name, isChecked, taskLabel);
        this.deadline = deadline;
    }

    public String isTaskCheck() {
        return super.getChecked() ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + isTaskCheck() + "] " + super.toString() + " (by: " + this.deadline + ")";
    }
}
