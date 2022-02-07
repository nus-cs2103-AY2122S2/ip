package duke;

/**
 * A simple Task object, supports Task description
 */
public class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        assert time.isPresent() : "time of Deadline not found";

        String doneIndicator = " ";
        if (isDone) {
            doneIndicator = "X";
        } else {
            doneIndicator = " ";
        }

        return "[T][" + doneIndicator + "] "
                + description;
    }

}