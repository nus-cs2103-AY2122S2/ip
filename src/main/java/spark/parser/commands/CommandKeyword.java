package spark.parser.commands;

/**
 * Commands that can be used in Spark
 */
public enum CommandKeyword {
    /** Tells Spark to exit. */
    BYE("bye"),
    /** Tells Spark to list all Tasks. */
    LIST("list"),
    /** Tells Spark to mark a Task as complete. */
    MARK("mark"),
    /** Tells Spark to mark a Task as incomplete. */
    UNMARK("unmark"),
    /** Tells Spark to delete a Task. */
    DELETE("delete"),
    /** Tells Spark to add a new Event to the task list. */
    EVENT("event"),
    /** Tells Spark to add a new Deadline to the task list. */
    DEADLINE("deadline"),
    /** Tells Spark to add a new Todo to the task list. */
    TODO("todo"),
    /** Tells Spark to find all Tasks that contain the given search-term. */
    FIND("find"),
    /** Tells Spark to alert the user that the given command-keyword is unrecognised. */
    UNRECOGNISED("unrecognised");

    private String commandKeyword;

    CommandKeyword(String commandKeyword) {
        this.commandKeyword = commandKeyword;
    }

    /**
     * Returns the relevant Command matching the command-keyword
     * in the user's input.
     *
     * @param commandKeyword a command-keyword in the user's input
     * @return               a Command to be executed by Spark
     */
    public static CommandKeyword getCommand(String commandKeyword) {
        CommandKeyword commandKeywords;

        try {
            commandKeywords = CommandKeyword.valueOf(commandKeyword.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandKeywords = CommandKeyword.UNRECOGNISED;
        }

        return commandKeywords;
    }

    /**
     * Returns the length of the command-keyword in
     * the user's input.
     *
     * @return length of the matching command-keyword
     */
    public int getCommandKeywordLength() {
        return this.commandKeyword.length();
    }
}
