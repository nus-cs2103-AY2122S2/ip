package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getDescription() {
        String newReply = super.description.replace("todo ", "");
        return "[T]" + "[" + super.getStatusIcon() + "] " + newReply;
    }
}
