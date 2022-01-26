public class ToDoTask extends Task {
    public ToDoTask(String message) throws EmptyCommandException {
        super(message);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeToFile() {
        return "todo "+ super.writeToFile();
    }
}
