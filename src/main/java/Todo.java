public class Todo extends Task {
    public Todo(String description) {
        super(description, 'T');
    }
    
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                super.getStatusIcon(), super.getDescription());
    }
}
