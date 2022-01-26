package duke.task;

public class ToDo extends Task {

    public ToDo(String content) {
        super(content);
    }

    public ToDo(String content, boolean isDone) {
        super(content, isDone);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "[T][X] " + getContent();
        } else {
            return "[T][ ] " + getContent();
        }
    }
}
