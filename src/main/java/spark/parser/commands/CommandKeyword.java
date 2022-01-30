package spark.parser.commands;

public enum CommandKeyword {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    EVENT("event"),
    DEADLINE("deadline"),
    TODO("todo"),
    FIND("find"),
    UNRECOGNISED("unrecognised");

    private String commandKeyword;

    CommandKeyword(String commandKeyword) {
        this.commandKeyword = commandKeyword;
    }

    public static CommandKeyword getCommand(String commandKeyword) {
        CommandKeyword commandKeywords;

        try {
            commandKeywords = CommandKeyword.valueOf(commandKeyword.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandKeywords = CommandKeyword.UNRECOGNISED;
        }

        return commandKeywords;
    }

    public int getCommandKeywordLength() {
        return this.commandKeyword.length();
    }
}
