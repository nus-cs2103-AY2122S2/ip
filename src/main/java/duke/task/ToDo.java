package duke.task;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(String taskName, String isMarked) {
        super(taskName);
        this.isMarked = (isMarked.equals("1"));
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String toFile() {
        String markStatus = super.isMarked ? "1" : "0";
        return String.format("%s | %s | %s\n", "T", markStatus, super.taskName);
    }
}
