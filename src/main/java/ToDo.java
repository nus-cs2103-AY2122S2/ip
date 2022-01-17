public class ToDo extends Task {
    public ToDo(String message) throws EmptyMessageException {
        super(message);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
