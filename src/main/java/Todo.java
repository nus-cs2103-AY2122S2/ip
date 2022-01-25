public class Todo extends Task {
    static final char TODO_SYMBOL = 'T';

    public Todo() {
        super();
    }

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    public Todo(boolean isDone, String taskDescription) {
        super(isDone, taskDescription);
    }

    @Override
    public String saveFileFormat() {
        return TODO_SYMBOL + "|" + isDone + "|" + taskDescription + "\n";
    }

    @Override
    public String toString() {
        return "[" + TODO_SYMBOL + "]" + super.toString();
    }
}
