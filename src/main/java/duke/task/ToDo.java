package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String taskDescriptionForFile() {
        int i = super.isDone ? 1 : 0;
        return "T , " + i + " , " + this.description.trim();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
