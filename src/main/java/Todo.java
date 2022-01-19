public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T]").append(super.toString());
        return sb.toString();
    }
}
