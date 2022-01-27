package duke.task;

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private String initial;

    /**
     * Constructor of a task type to associate with each type.
     *
     * @param initial Initial of task type
     */
    TaskType(String initial) {
        this.initial = initial;
    }

    /**
     * Getter of task type initial.
     *
     * @return Task type initial in a string
     */
    public String getInitial() {
        return this.initial;
    }
}
