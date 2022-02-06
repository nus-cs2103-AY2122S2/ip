package duke.taskobjects;

/**
 * Enumerator representing the different types of Tasks and it's String representation.
 */
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
