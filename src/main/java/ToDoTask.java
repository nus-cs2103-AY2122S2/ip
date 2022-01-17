public class ToDoTask extends Task {
    public ToDoTask(String message) throws EmptyMessageException {
        super(message);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
