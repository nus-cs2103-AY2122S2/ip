package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]"+ super.toString();
    }

    @Override
    public String getDetail(){
        int status = isDone ? 1 : 0;
        return "T" + " | " + status + " | " + this.description + "\n";
    }
}
