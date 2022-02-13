package dazz;

import java.util.Arrays;
import java.util.HashMap;

public class CommandMapper {
    private static final String ALIAS = "alias";
    private static final String BYE = "bye";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String EVENT = "event";
    private static final String FIND = "find";
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";

    private static final HashMap<String, String> commandDictionary = new HashMap<>();

    /**
     * Loads all the valid command into the <code>commandDictionary</code>
     */
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

    /**
     * Gets the command that the alias maps to.
     * @param key The alias.
     * @return The command i.e todo, deadline etc.
     */
    public static String getCommand(String key) {
        return commandDictionary.get(key);
    }

    /**
     * Creates a mapping to valid commands.
     * @param newAlias The alias.
     * @param command The command i.e todo, deadline etc.
     * @return true if there is no such alias that exists.
     */
    public static boolean putAlias(String newAlias, String command) {
        boolean hasAlias = commandDictionary.containsKey(newAlias);
        boolean isValid = isValidCommand(command);
        if (hasAlias || !isValid) {
            return false;
        } else {
            commandDictionary.put(newAlias, command);
            return true;
        }
    }

    private static boolean isValidCommand(String command) {
        String[] commands = new String[]{
            ALIAS, BYE, DEADLINE, DELETE, EVENT,
            FIND, HELP, LIST, MARK, UNMARK, TODO};
        return Arrays.asList(commands).contains(command);
    }

}
