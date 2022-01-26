package duke.task;

import java.util.StringJoiner;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveData() {
        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add("T").add(String.valueOf(isDone ? 1 : 0)).add(description);
        return joiner.toString();
    }
}
