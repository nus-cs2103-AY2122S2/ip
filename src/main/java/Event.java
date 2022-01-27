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
    private final DateHandler time;

    /**
     * Constructor for event class.
     * @param message the text given by the user.
     * returns a new instance of Event class.
     * @throw WrongDateArgumentException if the user inputs an invalid date.
     * @throw WrongTimeArgumentException if the user inputs an invalid time.
     */
    Event(String message) {
        super(message.split(SEPARATOR, LIMIT)[TSK_INDEX]);
        String str = message.split(SEPARATOR, LIMIT)[ET_IDX];
        DateHandler.checkValidDate(str.split(SPACE, LIMIT)[ET_IDX].trim());
        this.time = new DateHandler(str.split(SPACE, LIMIT)[ET_IDX].trim());
    }

    /**
     * checks if there are any events before date.
     * @param date the string given by user.
     * @return return if there is an event before date; false otherwise.
     */
    @Override
    boolean isBefore(String date) {
        return this.time.isBefore(date.trim());
    }

    /**
     * checks if there are any events on date.
     * @param date the string given by user.
     * @return return if there is an event on date; false otherwise.
     */
    @Override
    boolean isOnDate(String date) {
        return this.time.isOnDate(date.trim());
    }


    /**
     * correctArgument checks if the function is valid.
     * @param text the task input given by user.
     * @return true if correct.
     * @throws MissingTimeArgumentException if the user missed time argument out.
     */
    static boolean correctArgument(String text) {

        if (!text.contains(SEPARATOR) || !text.contains(TIME_ARGUMENT) || text.trim().split(TIME_ARGUMENT).length == 1) {
            throw new MissingTimeArgumentException("Event " + text);
        }

        return true;
    }

    /**
     * toString returns the string representation of the Event object.
     * @return the string representation of the instance.
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + "(at: " + this.time + ")";
    }
}
