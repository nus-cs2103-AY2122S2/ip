package istjbot.command;

import istjbot.exception.BotException;

/**
 * Commands that are understood by the IstjBot.
 */
public enum CommandEnum {
    TODO,
    DEADLINE,
    EVENT,
    DATE,
    FIND,
    MARK,
    UNMARK,
    LIST,
    BYE,
    DELETE;

    /**
     * Returns a CommandEnum after reading in String representation of command.
     *
     * @param info String representation of command.
     * @return An appropriate CommandEnum.
     * @throws BotException When the String command does not match with any of the existing CommandEnums.
     */
    public static CommandEnum stringToCommandEnum(String info) throws BotException {
        for (CommandEnum commandEnum : values()) {
            if (info.equalsIgnoreCase(commandEnum.toString())) {
                return commandEnum;
            }
        }
        throw new BotException("As an IstjBot, I don't understand what that command means.");
    }
}
