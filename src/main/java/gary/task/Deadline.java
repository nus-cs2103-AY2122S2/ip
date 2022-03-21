package gary.task;
/**
 * Represents a Task that entails a deadline
 * A completed DeadLine e.g. D[X] deadline_name by: 19-01-2022 10:30pm
 */
public class Deadline extends Task {
    private String date;

    /**
     * Constructs a new DeadLine object
     *
     * @param str deadline name
     * @param date due date
     */
    public Deadline(String str, String date) {
        super(str);
        this.date = date;
    }

    /**
     * Checks if Deadline name or date matches a specific keyword
     * Invoked by gary.TaskList::find
     *
     * @param str keyword
     * @return Whether the Deadline contains a specific keyword
     */
    @Override
    public boolean contain(String str) {
        return date.contains(str) || super.contain(str);
    }

    /**
     * Represents Deadline as a String object
     * e.g. D[X] deadline_name by: 09-12-2022 10:30pm
     *
     * @return Deadline representation in String
     */
    @Override
    public String toString() {
        return "D" + super.toString() + " by: " + date;
    }
}
