package Tasks;

/**
 * This class encapsulates an Event Task that occurs at a specific time frame.
 *
 * It has a start and end time.
 *
 * @author Ong Han Yang
 */
public class EventTask extends Task {
    /** start/end time of the event, temporarily as a String */
    private String startEndTime;

    /**
     * Constructor for an Event Task.
     * @param desc description for the task
     * @param startEndTime the start/end time of the task.
     */
    public EventTask(String desc, String startEndTime) {
        super(desc);
        this.startEndTime = startEndTime;
    }

    /**
     * Overridden toString method to represent the EventTask as a String.
     *
     * @return the String representation of an EventTask.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startEndTime);
    }

    /**
     * Method to parse a String to obtain an Event Task.
     *
     * Expected format: event <desc> /at <time>
     *
     * @param input the String command used to build the Task from.
     * @return the EventTask obtained.
     * @throws InvalidInputException when no description is given or no timing is given.
     */
    public static EventTask parseInput(String input) throws InvalidInputException {
        String desc = input.substring(5);
        if (desc.length() <= 1) {
            throw new InvalidInputException("No description or timing is provided for the Event Task!");
        }
        desc = desc.strip();
        int indexOfSplit = desc.indexOf(" /at ");
        if (indexOfSplit == -1) {
            throw new InvalidInputException("No \" /at \" found in the input!");
        }
        String[] params = desc.split(" /at ");
        if (params[0].length() == 0) {
            throw new InvalidInputException("No description is provided for the Event Task!");
        }
        if (params[1].length() == 0) {
            throw new InvalidInputException("No timing is provided for the Event Task!");
        }
        return new EventTask(params[0], params[1]);
    }
}
