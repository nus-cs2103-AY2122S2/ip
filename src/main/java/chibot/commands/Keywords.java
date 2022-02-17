package chibot.commands;

import chibot.exception.ChiException;

import java.util.HashMap;

/**
 * Enum of command keywords.
 *
 * @author WJunHong-reused
 * Reused from https://github.com/mslevis/ip/blob/master/src/main/java/aoi/commands/Keyword.java
 * with some modifications
 */
public enum Keywords {
    ADD("todo", "deadline", "event"),
    LIST("list"),
    DELETE("delete"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark"),
    HELP("help");

    /** A String containing all commands supported by ChiBot */
    public static final String listOfCommands = "\n1.list\n2.todo\n3.deadline\n4.event\n5.mark\n6.unmark\n7.delete"
            + "\n8.find\n9.help\n10.bye";

    /** mapping of keywords to their respective enum instances */
    private final HashMap<String, Keywords> keywordMappings = new HashMap<>();

    /**
     * Constructor of an enum instance.
     *
     * @param args The possible commands a user can use to map to the specific enum.
     */
    Keywords(String ... args) {
        for (String s: args) {
            keywordMappings.put(s, this);
        }
    }

    /**
     * Searches the keyword hashmap and returns the appropriate Keyword if the token matches the possible command.
     *
     * @param token The command word the user used in their message.
     * @return A Keyword corresponding to the command.
     * @throws ChiException If the user uses a keyword not defined in the enum.
     */
    public static Keywords getKeyword(String token) throws ChiException {
        for (Keywords k: Keywords.values()) {
            Keywords userKeyword = k.keywordMappings.get(token.toLowerCase());
            if (userKeyword != null) {
                return userKeyword;
            }
        }
        throw new ChiException("Command not found nyan! Please enter one of the following commands:\n"
                + listOfCommands + "\n\nIf you need help, type: help <command> to find out how to use it!");
    }

}
