package duke.task;

import java.util.StringTokenizer;

public class Todo extends Task {
    static final char TODO_SYMBOL = 'T';

    public Todo() {
        super();
    }

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String saveFileFormat() {
        return TODO_SYMBOL + "|" + isDone + "|" + taskDescription + "\n";
    }

    @Override
    public void extractFileData(String data) {
        StringTokenizer st = new StringTokenizer(data, "|");

        st.nextToken(); // remove the type symbol
        isDone = Boolean.parseBoolean(st.nextToken());
        taskDescription = st.nextToken();
    }

    @Override
    public String toString() {
        return "[" + TODO_SYMBOL + "]" + super.toString();
    }
}
