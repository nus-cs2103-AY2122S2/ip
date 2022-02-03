package istjbot.parser;

import istjbot.command.AddCommand;
import istjbot.command.ByeCommand;
import istjbot.command.Command;
import istjbot.command.CommandEnum;
import istjbot.command.DateCommand;
import istjbot.command.FindCommand;
import istjbot.command.ListCommand;
import istjbot.command.ModifyCommand;
import istjbot.exception.BotException;

/**
 * Encapsulates a Parser that is responsible for parsing the user's String command
 * and delegating to an appropriate Command class for actual execution.
 */
public class Parser {

    /**
     * Returns an appropriate Command to execute after reading in the String of the user's command.
     *
     * @param fullCommand String of the user's full command.
     * @return An appropriate Command.
     * @throws BotException When the user's input does not match any of the existing Commands.
     */
    public static Command parse(String fullCommand) throws BotException {
        CommandEnum commandEnum = CommandEnum.stringToCommandEnum(fullCommand.split(" ")[0]);
        switch (commandEnum) {
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(commandEnum, fullCommand);

        case MARK:
        case UNMARK:
        case DELETE:
            return new ModifyCommand(commandEnum, fullCommand);

        case DATE:
            return new DateCommand(commandEnum, fullCommand);

        case FIND:
            return new FindCommand(commandEnum, fullCommand);

        case LIST:
            return new ListCommand(commandEnum, fullCommand);

        case BYE:
            return new ByeCommand(commandEnum, fullCommand);

        default:
            throw new BotException("As an IstjBot, I cannot understand that command.");
        }
    }
}
