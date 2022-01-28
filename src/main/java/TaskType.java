/**
 * An enumeration that defines different task types.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String abbreviation;

    /**
     * Constructor to initialize an instance of TaskType enumeration
     * with abbreviation field.
     *
     * @param abbreviation Abbreviation of the task type
     */
    TaskType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the abbreviation of the task type.
     *
     * @return The abbreviation of the task type
     */
    public String getAbbreviation() {
        return abbreviation;
    }
}
