package pac.task;

import pac.PacException;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

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

    @Override
    public void rescheduleDate(String datetimeStr) throws PacException{
        throw new PacException("Cannot reschedule todo task.");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
