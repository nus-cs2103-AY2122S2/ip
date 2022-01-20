public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;

    public static Command getCommand(String[] str) throws InvalidCommandException {
        try {
            return Command.valueOf(str[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("huh?!\nPlease enter a valid command!");
        }
    }
}