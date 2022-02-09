package dazz;

import java.util.HashMap;

public class CommandMapper {
    private final static String ALIAS = "alias";
    private final static String BYE = "bye";
    private final static String DEADLINE = "deadline";
    private final static String DELETE = "delete";
    private final static String EVENT = "event";
    private final static String FIND = "find";
    private final static String HELP = "help";
    private final static String LIST = "list";
    private final static String MARK = "mark";
    private final static String UNMARK = "unmark";
    private final static String TODO = "todo";

    private static final HashMap<String, String> commandDictionary = new HashMap<>();

    public static void loadExistingMapping() {
        commandDictionary.put(ALIAS, ALIAS);
        commandDictionary.put(BYE, BYE);
        commandDictionary.put(DEADLINE, DEADLINE);
        commandDictionary.put(DELETE, DELETE);
        commandDictionary.put(EVENT, EVENT);
        commandDictionary.put(FIND, FIND);
        commandDictionary.put(HELP, HELP);
        commandDictionary.put(LIST, LIST);
        commandDictionary.put(MARK, MARK);
        commandDictionary.put(UNMARK, UNMARK);
        commandDictionary.put(TODO, TODO);
    }

    public static String getCommand(String key) {
        return commandDictionary.get(key);
    }

    public static boolean putAlias(String newAlias, String command) {
        boolean hasAlias = commandDictionary.containsKey(newAlias);
        if (hasAlias) {
            return false;
        } else {
            commandDictionary.put(newAlias, command);
            return true;
        }
    }

}
