package kidsnd274.duke.taskobjects;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String getCurrentStatus() {
        return "[T]" + super.getCurrentStatus();
    }

    @Override
    public String getType() {
        return "Todo Task";
    }
}
