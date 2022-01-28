import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    private String deadline;
    private Time time;

    public Deadline(String name, boolean isChecked, String taskLabel, String deadline) {
        super(name, isChecked, taskLabel);
//        try {
//
            Time time = new Time(deadline);
            this.time = time;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
//        }

    }

    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + super.isTaskCheck() + "] " + super.toString() + " (by: " + this.time + ")";
    }
}
