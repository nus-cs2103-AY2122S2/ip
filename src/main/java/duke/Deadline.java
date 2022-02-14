package duke;

/**
 * Represent a deadline task
 * It corresponds to a deadline represent by a string of task and the time
 */
public class Deadline extends Task{

    /**
     * Construts a deadline
     *
     * @param task description of the task
     * @param time time of the deadline
     */
    Deadline(String task, String time) {
        super(task, "D", time);
    }

    /**
     * Overrides Task.saveFormat by adding the time of deadline
     *
     * @return String in the standard saving format
     */
    @Override
    public String saveFormat() {
        return super.saveFormat() + " ### " + this.time;
    }

    /**
     * Overrides Task.toString() by adding the date and convert into standard format
     *
     * @return String in the standard printing format
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + this.date.format(formatter) + ")" + this.tag;
    }
}
