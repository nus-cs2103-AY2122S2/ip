package tasks;

import exceptions.MissingTimeArgumentException;
import helper.DateHandler;

/**
 * <h1>Event</h1>
 * <p>
 * Event handles tasks that occur at a certain time.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class Event extends Task {

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

    // The argument preceding time.
    private static final String TIME_ARGUMENT = "/at";

    // stores the time argument indicator in the output string.
    private static final String TIME_ARGUMENT_OUTPUT = "(at: ";

    // stores the start index value.
    private static final int START_INDEX = 0;

    // used to differentiate between constructors
    private static final int DUMMY_VARIABLE = 1;

    // instance variable to store event time.
    private final DateHandler time;

    /**
     * Constructor for event class.
     * @param message the text given by the user.
     * returns a new instance of Event class.
     */
    public Event(String message) {
        super(message.split(SEPARATOR, LIMIT)[TSK_INDEX]);
        String str = message.split(SEPARATOR, LIMIT)[ET_IDX];
        DateHandler.checkValidDate(str.split(SPACE, LIMIT)[ET_IDX].trim());
        this.time = new DateHandler(str.split(SPACE, LIMIT)[ET_IDX].trim());
    }

    /**
     * Constructor for Event class.
     * @param str string output of Event.
     * @param dummyVariable int to differentiate from other constructor.
     */
    public Event(String str, int dummyVariable) {
        super(str.substring(SYMBOL.length(), str.indexOf(TIME_ARGUMENT_OUTPUT)).trim(), DUMMY_VARIABLE);
        String temp = str.substring(str.indexOf(TIME_ARGUMENT_OUTPUT) + TIME_ARGUMENT_OUTPUT.length());
        temp = temp.trim().substring(START_INDEX, temp.length() - 1); // removes ")".
        this.time = new DateHandler(temp, DUMMY_VARIABLE);
    }

    /**
     * checks if there are any events before date.
     * @param date the string given by user.
     * @return return if there is an event before date; false otherwise.
     */
    @Override
    public boolean isBefore(String date) {
        return this.time.isBefore(date.trim());
    }

    /**
     * checks if there are any events on date.
     * @param date the string given by user.
     * @return return if there is an event on date; false otherwise.
     */
    @Override
    public boolean isOnDate(String date) {
        return this.time.isOnDate(date.trim());
    }

    /**
     * correctArgument checks if the function is valid.
     * @param text the task input given by user.
     * @return true if correct.
     * @throws MissingTimeArgumentException if the user missed time argument out.
     */
    public static boolean correctArgument(String text) {

        if (!text.contains(SEPARATOR)
                || !text.contains(TIME_ARGUMENT)
                || text.trim().split(TIME_ARGUMENT).length == 1) {
            throw new MissingTimeArgumentException("Event " + text);
        }

        return true;
    }

    /**
     * checks if the string is a Event task.
     * @param str the string representation of the task.
     * @return true if  the task is Event.
     */
    public static boolean isEvent(String str) {
        return str.substring(START_INDEX, SYMBOL.length()).contains(SYMBOL);
    }

    /**
     * toString returns the string representation of the Event object.
     * @return the string representation of the instance.
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + SPACE + TIME_ARGUMENT_OUTPUT + this.time + ")";
    }
}
