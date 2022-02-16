package chatcat.tasks;

import chatcat.chatcatexception.TaskException;
import chatcat.util.OutputMessage;

/**
 * The default Deadline class inherited from {@code Task}.
 *
 * @see Task
 */
public class Deadline extends Task {
    String deadlineStr;
    String time;

    /**
     * Creates a {@code Deadline} object using a specified description.
     *
     * @param deadlineStr the description of this task.
     * @param time the time of the task.
     */
    public Deadline(String deadlineStr, String time) {
        super(deadlineStr);
        this.deadlineStr = deadlineStr;
        this.time = time;
    }

    /**
     * Returns a representation in string of {@code Deadline} task.
     *
     * @return a representation in string of {@code Deadline} task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }

    /**
     * Checks if this deadline {@code Deadline} instance is the same as
     * another deadline {@code Deadline} instance.
     *
     * Logic for duplicate extension was referenced from Ng Jun Kang
     * https://github.com/ngjunkang/ip.git
     *
     * @param o object {@code Object} to compare with this deadline {@code Deadline} instance.
     * @return true if parameter has the same description as this
     * deadline {@code Deadline} instance.
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Deadline deadline = (Deadline) o;

        boolean isDateEqual = this.time.equals(deadline.time);
        boolean isDeadlineTypeEqual = this.deadlineStr.equals(deadline.deadlineStr);
        boolean isDeadlineEqual = isDateEqual && isDeadlineTypeEqual;
        return isDeadlineEqual;
    }
}
