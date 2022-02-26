package model;

public class TodoTask extends Task{
    public TodoTask(String task, boolean isComplete) {
        super(task, isComplete);
    }

    public TodoTask(String task) {
        super(task);
    }

    public static TodoTask of(String taskBody) {
        return new TodoTask(taskBody);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getType() {
        return "Todo";
    }
}
