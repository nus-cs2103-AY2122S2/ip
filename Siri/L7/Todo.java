public class Todo extends Task {

    public Todo(String description, String initialLetter) {
        super(description, "T");
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

}
