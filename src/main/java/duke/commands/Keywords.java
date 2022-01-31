package duke.commands;

import duke.exception.ChiException;

import java.util.HashMap;

public enum Keywords {
    ADD("todo","deadline","event"),
    LIST("list"),
    DELETE("delete"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark");

    private final HashMap<String, Keywords> keywordMappings = new HashMap<>();

    Keywords(String ... args) {
        for (String s: args) {
            keywordMappings.put(s,this);
        }
    }

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
