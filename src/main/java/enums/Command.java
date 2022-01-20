package enums;

public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
