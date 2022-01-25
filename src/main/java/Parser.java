public class Parser {

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

        case LIST:
            return new ListCommand(commandEnum, fullCommand);

        case BYE:
            return new ByeCommand(commandEnum, fullCommand);

        default:
            throw new BotException("As an IstjBot, I cannot understand that command.");
        }
    }
}
