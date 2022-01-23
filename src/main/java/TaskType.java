public enum TaskType {
    DEADLINE("D"), EVENT("E"), TODO("T");

    private final String taskType;

    TaskType(String s) {
        taskType = s;
    }

    public String getTaskType() {
        return taskType;
    }
}
