package saitama.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getTaskData() {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        return "T " + isDone + " " + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}