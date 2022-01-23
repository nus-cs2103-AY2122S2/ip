public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    LIST,
    BYE,
    DELETE;

    public static Command stringToType(String info) {
        for (Command command : values()) {
            if (command.toString().equalsIgnoreCase(info)) {
                return command;
            }
        }
    }
}
