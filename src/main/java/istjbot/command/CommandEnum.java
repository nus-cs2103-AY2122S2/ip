package istjbot.command;

import istjbot.exception.BotException;

public enum CommandEnum {
    TODO,
    DEADLINE,
    EVENT,
    DATE,
    MARK,
    UNMARK,
    LIST,
    BYE,
    DELETE;

    public static CommandEnum stringToCommandEnum(String info) throws BotException {
        for (CommandEnum commandEnum : values()) {
            if (info.equalsIgnoreCase(commandEnum.toString())) {
                return commandEnum;
            }
        }
        throw new BotException("As an IstjBot, I don't understand what that command means.");
    }
}
