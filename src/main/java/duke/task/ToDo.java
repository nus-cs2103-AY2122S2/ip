package duke.task;

public class ToDo extends Task {

    private boolean done;
    private String description;


    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
