/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Event class.
 */

public class Event extends Task{

    // Symbol for event class.
    private static final String SYMBOL = "[E]";

    // Separator to distinguish task and event time.
    private static final String SEPARATOR = "/";

    // Separator to split deadline substring.
    private static final String SPACE = " ";

    // The index value of task string in split array.
    private static final int TSK_INDEX = 0;

    // the index value of event time string in split array.
    private static final int ET_IDX = 1;

    // Limits the splitting of user message into substrings.
    private static final int LIMIT = 2;

    private static final String TIME_ARGUMENT = "/at";

    // instance variable to store event time.
    private final String time;

    /**
     * Constructor for event class.
     * @param message the text given by the user.
     * returns a new instance of Event class.
     */
    Event(String message) {
        super(message.split(SEPARATOR, LIMIT)[TSK_INDEX]);
        String str = message.split(SEPARATOR, LIMIT)[ET_IDX];
        this.time = str.split(SPACE, LIMIT)[ET_IDX];
    }

    /**
     * correctArgument checks if the function is valid.
     * @param text the task input given by user.
     * @return true if correct.
     * @throws MissingTimeArgumentException if the user missed time argument out.
     */
    static boolean correctArgument(String text) {

        if (!text.contains(SEPARATOR) || !text.contains(TIME_ARGUMENT) || text.trim().split(TIME_ARGUMENT).length == 1) {
            throw new MissingTimeArgumentException("Deadline " + text);
        }

        return true;
    }

    /**
     * toString returns the string representation of the Event object.
     * @return the string representation of the instance.
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + "(at:" + this.time + ")";
    }
}
