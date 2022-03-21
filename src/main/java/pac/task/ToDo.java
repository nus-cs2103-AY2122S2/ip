package pac.task;

import pac.PacException;

/**
 *  For todo tasks
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the String that is written into data file
     * @return
     */
    @Override
    public String toWrite() {
        int bool;

        if(isDone) {
            bool = 1;
        } else {
            bool = 0;
        }

        return "T~" + bool + "~" + getDescription() + System.lineSeparator();
    }

    /**
     * throws PacException
     * @param datetimeStr
     * @throws PacException
     */
    @Override
    public void rescheduleDate(String datetimeStr) throws PacException{
        throw new PacException("Cannot reschedule todo task.");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
