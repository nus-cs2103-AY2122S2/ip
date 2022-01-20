package baron.tasks;

public enum TaskType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String command;

    private TaskType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}
