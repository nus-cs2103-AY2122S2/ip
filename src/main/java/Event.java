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
     * toString returns the string representation of the Event object.
     * @return the string representation of the instance.
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + "(at:" + this.time + ")";
    }
}
