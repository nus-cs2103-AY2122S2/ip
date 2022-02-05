public class Todo extends Task {
    private String date;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
