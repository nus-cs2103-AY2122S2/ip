package duke.task;

import java.util.Arrays;

public enum TaskType {
    TODO("T", 1),
    DEADLINE("D", 2),
    EVENT("E", 3);

    private final String shorthand;
    private final int typeId;
    TaskType(String shorthand, int typeId) {
        this.shorthand = shorthand;
        this.typeId = typeId;
    }

    public String getShorthand() {
        return this.shorthand;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public static TaskType matchType(int typeId) {
        return Arrays.stream(TaskType.values()).filter(x -> x.getTypeId() == typeId)
                .findFirst()
                .orElse(null);
    }
}
