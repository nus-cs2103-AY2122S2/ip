package tasks;

import exceptions.MissingTimeArgumentException;
import helper.DateHandler;

import java.time.LocalDate;

/**
 * <h1>Deadline</h1>
 * <p>
 * Deadline handles tasks that are supposed to be completed before
 * a certain date.
 * </p>
 *
 * @author Saravanan Anuja Harish
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

    // The argument preceding time.
    private static final String TIME_ARGUMENT = "/by";

    // stores the time argument indicator in the output string.
    private static final String TIME_ARGUMENT_OUTPUT = "(by: ";

    // used to differentiate between constructors
    private static final int DUMMY_VARIABLE = 1;

    // stores the start index value.
    private static final int START_INDEX = 0;

    // instance variable to store deadline
    private final DateHandler deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param message the text given by the user.
     * return an instance of deadline.
     */
    public Deadline(String message) {

        super(message.split(SEPARATOR, LIMIT)[TSK_INDEX]);

        String str = message.split(SEPARATOR, LIMIT)[DD_IDX];
        DateHandler.checkValidDate(str.split(SPACE, LIMIT)[DD_IDX]);

        this.deadline = new DateHandler(str.split(SPACE, LIMIT)[DD_IDX]);
    }

    /**
     * Constructor for Deadline class.
     *
     * @param str string output of Deadline.
     * @param dummyVariable int to differentiate from other constructor.
     */
    public Deadline(String str, int dummyVariable) {

        super(str.substring(SYMBOL.length(), str.indexOf(TIME_ARGUMENT_OUTPUT)).trim(), DUMMY_VARIABLE);

        String temp = str.substring(str.indexOf(TIME_ARGUMENT_OUTPUT) + TIME_ARGUMENT_OUTPUT.length());
        temp = temp.trim().substring(START_INDEX, temp.length() - 1); // removes ")".

        this.deadline = new DateHandler(temp, DUMMY_VARIABLE);
    }

    /**
     * correctArgument checks if the function is valid.
     *
     * @param text the task input given by user.
     * @return true if correct.
     * @throws MissingTimeArgumentException if the user missed time argument out.
     */
    public static boolean correctArgument(String text) {

        boolean isSeparatorAbsent = !text.contains(SEPARATOR);
        boolean isTimeAbsent = !text.contains(TIME_ARGUMENT);
        boolean isTimeArgumentAbsent = text.trim().split(TIME_ARGUMENT).length == 1;

        if (isSeparatorAbsent || isTimeAbsent || isTimeArgumentAbsent) {
            throw new MissingTimeArgumentException("Deadline " + text);
        }

        return true;
    }

    /**
     * checks if the task deadline is due before date.
     *
     * @param date the string given by user.
     * @return return if the task is due before date; false otherwise.
     */
    @Override
    public boolean isBefore(String date) {
        return this.deadline.isBefore(date.trim());
    }

    /**
     * checks if the task deadline is due on date.
     *
     * @param date the string given by user.
     * @return return if the task is due on date; false otherwise.
     */
    @Override
    public boolean isOnDate(String date) {
        return this.deadline.isOnDate(date.trim());
    }

    /**
     * checks if the task deadline is due on date.
     *
     * @param date the string given by user.
     * @return return if the task is due on date; false otherwise.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return this.deadline.isOnDate(date);
    }

    /**
     * returns the date on which the task is due.
     *
     * @return the date associated with task as LocalDate.
     */
    @Override
    public LocalDate getDate() {
        return this.deadline.getDate();
    }

    /**
     * checks if the string is a Deadline task.
     *
     * @param str the string representation of the task.
     * @return true if  the task is Deadline.
     */
    public static boolean isDeadline(String str) {
        return str.substring(START_INDEX, SYMBOL.length()).contains(SYMBOL);
    }

    /**
     * returns the string representation of the Deadline object.
     *
     * @return the string representation of the instance.
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + SPACE + TIME_ARGUMENT_OUTPUT + this.deadline + ")";
    }
}
