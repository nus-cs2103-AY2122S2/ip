package seedu.duke;

public class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        String tempStr = " ";

        if (isDone) {
            tempStr = "X";
        }

        return "[T][" + tempStr + "] " + description;
    }

}