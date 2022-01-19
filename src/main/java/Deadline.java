/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Deadline class.
 */

public class Deadline extends Task {

    // Symbol for Deadline class.
    private static final String SYMBOL = "[D]";

    // Separator to distinguish task and deadline.
    private static final String SEPARATOR = "/";

    // Separator to split deadline substring.
    private static final String SPACE = " ";

    // The index value of task string in split array.
    private static final int TSK_INDEX = 0;

    // the index value of deadline string in split array.
    private static final int DD_IDX = 1;

    // Limits the splitting of user message into substrings.
    private static final int LIMIT = 2;

    // instance variable to store deadline
    private final String deadline;

    /**
     * Constructor for Deadline class.
     * @param message the text given by the user.
     * return an instance of deadline.
     */
    Deadline(String message) {
        super(message.split(SEPARATOR, LIMIT)[TSK_INDEX]);
        String str = message.split(SEPARATOR, LIMIT)[DD_IDX];
        this.deadline = str.split(SPACE, LIMIT)[DD_IDX];
    }

    /**
     * toString returns the string representation of the Deadline object.
     * @return the string representation of the instance.
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + "(by:" + this.deadline + ")";
    }
}
