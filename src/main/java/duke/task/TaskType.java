package duke.task;

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private String initial;

    TaskType(String initial) {
        this.initial = initial;
    }

    public String getInitial() {
        return this.initial;
    }
}
