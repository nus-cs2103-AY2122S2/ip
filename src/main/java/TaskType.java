public enum TaskType {
    TODO("todo"), EVENT("event"), DEADLINE("deadline");
    private final String taskName;

    TaskType(String taskName) {
        this.taskName = taskName;
    }
}
