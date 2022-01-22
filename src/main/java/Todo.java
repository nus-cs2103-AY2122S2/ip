public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getAppendData() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + description ;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
