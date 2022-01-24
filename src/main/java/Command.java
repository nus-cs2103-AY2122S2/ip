public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    DATE,
    MARK,
    UNMARK,
    LIST,
    BYE,
    DELETE;

    public static Command stringToCommand(String info) throws BotException {
        for (Command command : values()) {
            if (info.equalsIgnoreCase(command.toString())) {
                return command;
            }
        }
        throw new BotException("As an ISTJBot, I don't understand what that command means.");
    }
}
