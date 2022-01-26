package enums;

public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    FIND;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
