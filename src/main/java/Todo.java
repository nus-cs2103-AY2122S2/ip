public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String formatString() {
        String output = "T";
        String markState = this.isDone ? "mark" : "unmark";
        return output + " | " + markState + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
