package duke.commands;

import duke.exception.ChiException;

import java.util.HashMap;

/**
 * Enum of command keywords.
 */
/*
    @author WJunHong-reused
    Reused from https://github.com/mslevis/ip/blob/master/src/main/java/aoi/commands/Keyword.java
    with some modifications
 */
public enum Keywords {
    ADD("todo","deadline","event"),
    LIST("list"),
    DELETE("delete"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark");

    /** mapping of keywords to their respective enum instances */
    private final HashMap<String, Keywords> keywordMappings = new HashMap<>();

    /**
     * Constructor of an enum instance.
     *
     * @param args The possible commands a user can use to map to the specific enum.
     */
    Keywords(String ... args) {
        for (String s: args) {
            keywordMappings.put(s,this);
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
            System.out.println(token);
            System.out.println(k);
            if (k.keywordMappings.get(token.toLowerCase()) != null) {
                return k.keywordMappings.get(token.toLowerCase());
            }
        }
        throw new ChiException("Command not found nyan!");
    }

}
