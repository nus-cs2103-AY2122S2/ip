package duke;

/**
 * Represents a {@link Task} without any time requirement.
 */
public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    /**
     * returns a string representation of the Task, to be read by the user.
     *
     * @return a string representation of the Task, to be read by the user
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * returns a string representation of the Task, to be stored locally.
     *
     * @return a string representation of the Task, to be stored locally
     */
    @Override
    public String toData() {
        String isFinishedData;
        if (super.finished) {
            isFinishedData = "1";
        } else {
            isFinishedData = "0";
        }
        return "T:" + isFinishedData + ":" + super.content;
    }
}
