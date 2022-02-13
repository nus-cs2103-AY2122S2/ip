package duke.taskobjects;

/**
 * Enumerator representing the different types of Tasks and its String representation.
 */
public enum Types {
    TODO("Todo Task"),
    EVENT("Event Task"),
    DEADLINE("Deadline Task");

    private final String str;
    Types(String string) {
        str = string;
    }

    /**
     * Returns the task type in String.
     *
     * @return Task type in String.
     */
    @Override
    public String toString() {
        return str;
    }
}
