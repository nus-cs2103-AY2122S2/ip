package enums;

/**
 * An enum class for user commands.
 */
public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    FIND,
    SNOOZE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
