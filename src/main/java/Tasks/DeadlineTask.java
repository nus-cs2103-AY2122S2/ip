package Tasks;

import java.util.Objects;

/**
 * This class encapsulates a Deadline Task.
 *
 * A Deadline task has a specific time that it has to be done by.
 *
 * @author Ong Han Yang
 */
public class DeadlineTask extends Task{
    /** The deadline for the task, temporarily in String format,*/
    private String deadline;

    /**
     * Constructor for a deadline task.
     *
     * @param desc the description for the task.
     * @param deadline the deadline for the task.
     */
    public DeadlineTask(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * Overridden toString method to represent the deadline task as a string.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    /**
     * Method to parse a String to obtain an Deadline Task.
     *
     * Expected format: deadline <desc> /by <time>
     *
     * @param input the String command used to build the Task from.
     * @return the DeadlineTask obtained.
     * @throws InvalidInputException when no description is given or no timing is given.
     */
    public static DeadlineTask parseInput(String input) throws InvalidInputException {
        String desc = input.substring(8);
        if (desc.length() <= 1) {
            throw new InvalidInputException("No description or timing is provided for the Deadline Task!");
        }
        desc = desc.strip();
        int indexOfSplit = desc.indexOf(" /by ");
        if (indexOfSplit == -1) {
            throw new InvalidInputException("No \" /by \" found in the input!");
        }
        String[] params = desc.split(" /by ");
        if (params[0].length() == 0) {
            throw new InvalidInputException("No description is provided for the Deadline Task!");
        }
        if (params[1].length() == 0) {
            throw new InvalidInputException("No timing is provided for the Deadline Task!");
        }
        return new DeadlineTask(params[0], params[1]);
    }
}
