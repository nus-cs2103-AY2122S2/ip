public class Todo extends Task {
    public Todo(String description, char type) {
        super(description, type);
    }
    
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                super.getStatusIcon(), super.getDescription());
    }
}
