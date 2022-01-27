public class Todo extends Task{
    protected boolean status;

    public Todo(boolean status, String description) {
        super(description);
        super.isDone = status;
    }

    @Override
    public String appendtoFile() {
        return "T|" + (super.isDone ? "1" : "0") + "|" + super.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
