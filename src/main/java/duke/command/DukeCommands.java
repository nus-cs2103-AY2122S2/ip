package duke.command;

import java.util.Arrays;
import java.util.HashMap;

public class DukeCommands {

    public enum DUKE_COMMANDS {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, FIND
    }

    public enum DUKE_DESCRIPTION_COMMANDS {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND
    }

    public static boolean isDukeCommand(String string) {
        return Arrays.stream(DUKE_COMMANDS.values())
                .anyMatch((command) -> command.name()
                        .equalsIgnoreCase(string));
    }

    public static boolean isDukeDescriptionCommand(String string) {
        return Arrays.stream(DUKE_DESCRIPTION_COMMANDS.values()).
                anyMatch((command) -> command.name().
                        equalsIgnoreCase(string));
    }

    public static HashMap<String, String> getTypeMap() {
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
        return h;
    }
}
