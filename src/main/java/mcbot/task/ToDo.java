package mcbot.task;

public class ToDo extends Task {
    
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }
    
    @Override
    public String toDataString() {
        String isDone = super.isMarked() ? "1" : "0";
        return getTaskIcon() + " | " + isDone + " | " + taskName;
    }

    @Override
    public String toString() {
        return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " + super.taskName;
    }
}
