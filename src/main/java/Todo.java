public class Todo extends Task {
    protected static String type = "TODO";
    
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
