package duke.command;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents valid Ekud commands. Ekud commands are categorised into
 * those needing a description and those without. A DukeCommand object
 * will provide method necessarily to check if a user input is a valid command.
 */
public class DukeCommand {

    private enum DukeCommands {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, FIND, CLEAR

    }

    private enum DescriptiveDukeCommands {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND
    }

    /**
     * Checks if the supplied string is a valid Ekud command.
     *
     * @param string Command supplied.
     * @return True if the command is a valid Ekud command, false otherwise.
     */
    public static boolean isDukeCommand(String string) {
        return Arrays.stream(DukeCommands.values())
                .anyMatch((command) -> command.name()
                        .equalsIgnoreCase(string));
    }

    /**
     * Checks if the supplied string is a valid Ekud command that
     * requires a description.
     *
     * @param string Command supplied.
     * @return True if the command is a valid Ekud command
     * needing a description, false otherwise.
     */
    public static boolean isDukeDescriptionCommand(String string) {
        return Arrays.stream(DescriptiveDukeCommands.values())
                .anyMatch((command) -> command.name()
                        .equalsIgnoreCase(string));
    }

    /**
     * A method that returns a hash map that has value of the type of command
     * corresponding to its key.
     *
     * @return Hashmap of type String, String with key-value pair of Ekud command, Command type.
     */
    public static HashMap<String, String> getTaskTypeMap() {
        HashMap<String, String> h = new HashMap<>();
        h.put("list", "OUTPUT_COMMAND");
        h.put("todo", "ADD_COMMAND");
        h.put("event", "ADD_COMMAND");
        h.put("deadline", "ADD_COMMAND");
        h.put("mark", "MARK_COMMAND");
        h.put("unmark", "UNMARK_COMMAND");
        h.put("delete", "DELETE_COMMAND");
        h.put("bye", "EXIT_COMMAND");
        h.put("find", "FIND_COMMAND");
        h.put("clear", "CLEAR_COMMAND");
        return h;
    }
}
