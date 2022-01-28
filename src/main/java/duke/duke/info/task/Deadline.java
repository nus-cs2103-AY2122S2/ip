package duke.info.task;

public class Deadline extends Task {

    /**
     * Constructs a Deadline object with the specified deadline, date and isComplete boolean.
     * @param deadline - the deadline string
     * @param date - the date of the deadline
     * @param isComplete - true if the deadline is complete
     */
    public Deadline(String deadline, String date, boolean isComplete) {
        super(deadline, "deadline", isComplete, date);
    }

    /**
     * Returns the save format of the Deadline. The string contains the save format representation
     * of the task superclass, followed by the string representation of the date as obtained from
     * the getDateString of the superclass method delimited by a "|".
     * @return - the save format of the Deadline as a string
     */
    @Override
    public String saveFormat() {
        return super.saveFormat() + "|" + this.getDateString();
    }

    /**
     * Returns a representation of the Deadline. The string consists of the type of the task
     * contained by "[]", the task description, followed by the date of the deadline as obtained
     * by the super.getDateString method.
     * contained by "()"
     * @return - a string representation of the Deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getDateString());
    }
}
