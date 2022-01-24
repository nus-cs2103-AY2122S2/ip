package kidsnd274.duke.taskobjects;

public enum Types {
    TODO("Todo Task"),
    EVENT("Event Task"),
    DEADLINE("Deadline Task");

    private final String str;
    Types(String string) {
        str = string;
    }

    @Override
    public String toString() {
        return str;
    }
}
