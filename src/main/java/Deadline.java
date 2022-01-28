public class Deadline extends Task{

    private String deadline;
    private Time time;

    public Deadline(String name, boolean isChecked, String taskLabel, String deadline) {
        super(name, isChecked, taskLabel);
        Time time = new Time(deadline);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + super.isTaskCheck() + "] " + super.toString() + " (by: " + this.time + ")";
    }
}
